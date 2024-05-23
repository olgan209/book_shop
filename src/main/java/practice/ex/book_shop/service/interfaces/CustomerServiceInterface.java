package practice.ex.book_shop.service.interfaces;

import practice.ex.book_shop.dto.customer.CustomerRequest;
import practice.ex.book_shop.dto.customer.CustomerResponse;

import java.util.List;

public interface CustomerServiceInterface {
    CustomerResponse find(Integer id, String token);

    void delete(Integer id);

    List<CustomerResponse> getAll();

    void update(Integer id, CustomerRequest customerRequest);
}
