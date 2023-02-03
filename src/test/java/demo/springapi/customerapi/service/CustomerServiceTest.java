package demo.springapi.customerapi.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import demo.springapi.customerapi.CustomerFaker;
import demo.springapi.customerapi.entity.Customer;
import demo.springapi.customerapi.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    public void testSaveAll() throws Exception {
        // Given
        List<Customer> newCustomerList = CustomerFaker.generateCustomerList(10);
        
        // When
        Mockito.when(repository.saveAll(newCustomerList)).thenReturn(newCustomerList);
        
        // Then
        List<Customer> savedList = customerService.saveAll(newCustomerList);
        Assertions.assertEquals(newCustomerList, savedList);
    }
}
