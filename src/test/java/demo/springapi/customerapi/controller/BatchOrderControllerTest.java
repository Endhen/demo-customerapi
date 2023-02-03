package demo.springapi.customerapi.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import demo.springapi.customerapi.entity.BatchOrder;
import demo.springapi.customerapi.service.BatchOrderServiceInterface;

@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = BatchOrderController.class)
public class BatchOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BatchOrderServiceInterface service;

    @Test
    public void testCreateNewBatchOrder() throws Exception {

        // Given
        long id = 1L;
        BatchOrder newOrder = new BatchOrder().setId(id);

        // When
        Mockito.when(service.createNewBatchOrder()).thenReturn(newOrder);
        
        // Then
        mockMvc.perform(MockMvcRequestBuilders.post("/batch-order/generate"))
                .andExpect(status().isAccepted())
                .andExpect(content().string("New import order successfully created with id " + id));
    }

    @Test
    public void testGetBatchOrder() throws Exception {

        // Given
        long requestedId = 1;
        BatchOrder batchOrder = new BatchOrder().setId(requestedId);
        Optional<BatchOrder> expectedBatchOrder = Optional.of(batchOrder);

        // When
        Mockito.when(service.findById(1)).thenReturn(expectedBatchOrder);
        
        // Then
        mockMvc.perform(MockMvcRequestBuilders.get("/batch-order/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is((int) requestedId)));
    }
}
