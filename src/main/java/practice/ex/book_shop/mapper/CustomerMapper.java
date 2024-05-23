package practice.ex.book_shop.mapper;

import practice.ex.book_shop.dto.customer.CustomerRequest;
import practice.ex.book_shop.dto.customer.CustomerResponse;
import practice.ex.book_shop.entities.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerMapper implements CustomerMap {

    @Override
    public CustomerResponse toDto(Customer customer) {
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(customer.getId());
        customerResponse.setFirstname(customer.getFirstname());
        return customerResponse;
    }

    @Override
    public List<CustomerResponse> toDto(List<Customer> all) {
        List<CustomerResponse> customerResponses = new ArrayList<>();
        for (Customer customer : all) {
            customerResponses.add(toDto(customer));
        }
        return customerResponses;
    }
}

