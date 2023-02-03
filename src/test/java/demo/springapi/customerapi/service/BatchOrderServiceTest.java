package demo.springapi.customerapi.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
        // TODO spy method and return type
        // TODO Test negatif
        // Given
        BatchOrder expectedBatchOrder = new BatchOrder().setId(1);
        
        // When
        Mockito.when(repository.save(Mockito.any())).thenReturn(expectedBatchOrder);
        // Mockito.when(repository.save(Mockito.any())).thenThrow(null);
        // TODO Voir si c'est bien dans la queue
        // ? Commetn avoir ce qui est passé dans Mockito.any() ? 
        
        // Then
        BatchOrder createdBatchOrder = batchOrderService.createNewBatchOrder();
        Assertions.assertEquals(expectedBatchOrder, createdBatchOrder);

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
