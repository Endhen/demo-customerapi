package demo.springapi.customerapi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.github.javafaker.Faker;

import demo.springapi.customerapi.entity.Customer;

public class CustomerFaker {
    
    public static List<Customer> generateCustomerList(int size) {

        Faker faker = new Faker();
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat dateToString = new SimpleDateFormat(pattern);
        List<Customer> newCustomerList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            newCustomerList.add(
                new Customer(
                    i,
                    faker.name().firstName(),
                    faker.name().lastName(),
                    faker.internet().emailAddress(),
                    new String[] {"Male", "Female"}
                            [faker.number().numberBetween(0, 1)],
                    faker.phoneNumber().phoneNumber(),
                    faker.country().name(),
                    dateToString.format(
                        faker.date().birthday())
                )
            );
        }

        return newCustomerList;
    }
}
