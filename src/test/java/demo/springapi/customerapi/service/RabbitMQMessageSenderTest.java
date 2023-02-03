package demo.springapi.customerapi.service;

import static org.mockito.Mockito.doNothing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RabbitMQMessageSenderTest {

    @Mock
    private RabbitMQMessageSender messageSenderService;

    @Test
    public void testSendOrder() {
        doNothing().when(messageSenderService).sendOrder(1l); // * Unnecessary stubbings
    }
}
