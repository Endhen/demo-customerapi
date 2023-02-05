package demo.springapi.customerapi.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.AmqpException;

import demo.springapi.customerapi.entity.BatchOrder;
import demo.springapi.customerapi.enums.OrderStatus;
import demo.springapi.customerapi.repository.BatchOrderRepository;

@ExtendWith(MockitoExtension.class)
public class BatchOrderServiceTest {

    @Mock
    private BatchOrderRepository repository;

    @Mock
    private MessageSenderInterface rabbitMQSender;

    @InjectMocks
    private BatchOrderService batchOrderService;

    @Test
    public void testCreateNewBatchOrder() throws Exception {
        // Given
        BatchOrder expectedBatchOrder = new BatchOrder().setId(1);
        
        // When
        Mockito.when(repository.save(any())).thenReturn(expectedBatchOrder);

        // Then
        BatchOrder createdBatchOrder = batchOrderService.createNewBatchOrder();

        Mockito.verify(rabbitMQSender, times(1)).sendOrder(anyLong());
        Assertions.assertEquals(expectedBatchOrder, createdBatchOrder);

    }

    @Test
    public void createNewBatchOrder_ThrowsException_IfMessageSendingFail() throws Exception {
        // Given
        BatchOrder expectedBatchOrder = new BatchOrder().setId(1);
        AmqpException expectedException = null;
        
        // When
        Mockito.when(repository.save(any())).thenReturn(expectedBatchOrder);
        Mockito.doThrow(AmqpException.class).when(rabbitMQSender).sendOrder(anyLong());

        // Then
        try {
            batchOrderService.createNewBatchOrder();
        } catch (AmqpException ae) {
            expectedException = ae;
        }

        Assertions.assertNotNull(expectedException);

    }

    @Test
    public void testFindById() throws Exception {

        // Given
        long id = 1l;
        BatchOrder targetedBatchOrder = new BatchOrder().setId(id);

        // When
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(targetedBatchOrder));
        
        // Then
        Optional<BatchOrder> createdBatchOrder = batchOrderService.findById(id);
        Assertions.assertEquals(targetedBatchOrder, createdBatchOrder.get());

    }

    @Test
    public void testUpdate() throws Exception {

        // Given
        long id = 1l;
        OrderStatus newStatus = OrderStatus.COMPLETED;
        BatchOrder expectedBatchOrder = new BatchOrder()
            .setId(id)
            .setStatus(newStatus);

        // When
        Mockito.when(repository.save(expectedBatchOrder)).thenReturn(expectedBatchOrder);
        
        // Then
        BatchOrder updatedBatchOrder = batchOrderService.update(expectedBatchOrder);
        Assertions.assertEquals(expectedBatchOrder, updatedBatchOrder);
    }
}
