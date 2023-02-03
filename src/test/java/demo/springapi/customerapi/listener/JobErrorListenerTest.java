package demo.springapi.customerapi.listener;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import demo.springapi.customerapi.service.BatchOrderService;

public class JobErrorListenerTest {

    @Mock
    private BatchOrderService batchOrderService;

    @InjectMocks
    private JobErrorListener jobErrorListener;

    @Test
    void testProcessBatchOrderError() {
        // Spy logger et service
        // Given

        // When 

        // Then 
        jobErrorListener.process(1l);

    }

    @Test
    void testProcessBatchOrderLost() {
        jobErrorListener.process(1l);
    }
}
