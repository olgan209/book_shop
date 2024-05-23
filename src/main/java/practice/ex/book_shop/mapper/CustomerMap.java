package practice.ex.book_shop.mapper;

import practice.ex.book_shop.dto.customer.CustomerResponse;
import practice.ex.book_shop.entities.Customer;

import java.util.List;

public interface CustomerMap {
    CustomerResponse toDto(Customer customer);

    List<CustomerResponse> toDto(List<Customer>all);
}
