package practice.ex.book_shop.service;

import org.springframework.stereotype.Service;
import practice.ex.book_shop.dto.customer.CustomerRequest;
import practice.ex.book_shop.dto.customer.CustomerResponse;
import practice.ex.book_shop.entities.Customer;
import practice.ex.book_shop.mapper.CustomerMapper;
import practice.ex.book_shop.repositories.CustomerRepository;
import practice.ex.book_shop.service.interfaces.CustomerServiceInterface;

import java.util.List;
import java.util.Optional;
@Service
public class CustomerService implements CustomerServiceInterface {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    @Override
    public CustomerResponse find(Integer id, String token) {
        Optional<Customer> customer=customerRepository.findById(id);
        if(customer.isEmpty()){
            System.out.println("repairer by id: "+id+" is empty");
            return null;
        }else{
            CustomerResponse customerResponse=new CustomerResponse();
            return customerMapper.toDto(customer.get());
        }
    }

    @Override
    public void delete(Integer id) {
        Optional<Customer> customer=customerRepository.findById(id);
        if(customer.isEmpty()){
            System.out.println("customer by id: "+id+" is empty");
        }else{
            customerRepository.deleteById(id);
        }
    }

    @Override
    public List<CustomerResponse> getAll() {
        return customerMapper.toDto(customerRepository.findAll());
    }

    @Override
    public void update(Integer id, CustomerRequest customerRequest) {
        Optional<Customer>customer=customerRepository.findById(id);
        if(customer.isEmpty()){
            System.out.println("Customer by id: "+id+" is empty");
        }else{
            customer.get().setFirstname(customerRequest.getFirstName());

            customerRepository.save(customer.get());
        }
    }
}
