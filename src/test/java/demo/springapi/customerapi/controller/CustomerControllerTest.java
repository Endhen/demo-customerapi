package demo.springapi.customerapi.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import demo.springapi.customerapi.CustomerFaker;
import demo.springapi.customerapi.entity.Customer;
import demo.springapi.customerapi.service.CustomerServiceInterface;

@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerServiceInterface customerService;

    @Test
    public void testImportCustomerList() throws Exception {

        // Given
        List<Customer> newCustomerList = CustomerFaker.generateCustomerList(10);
        ObjectMapper objectMapper = new ObjectMapper();

        // When
        mockMvc.perform(MockMvcRequestBuilders.post("/customer/list")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(newCustomerList)))
        // Then
                .andExpect(status().isCreated())
                .andExpect(content().string(newCustomerList.size() + "customers successfuly imported into data base"));
    }

}
