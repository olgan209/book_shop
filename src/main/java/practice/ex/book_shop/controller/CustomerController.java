package practice.ex.book_shop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import practice.ex.book_shop.dto.customer.CustomerRequest;
import practice.ex.book_shop.dto.customer.CustomerResponse;
import practice.ex.book_shop.service.CustomerService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    CustomerService customerService;

    @GetMapping("/find/{id}")
    public CustomerResponse find(@PathVariable Integer id, @RequestHeader("Authorization") String token){
        return customerService.find(id,token);
    }

    @GetMapping("/getAll")
    public List<CustomerResponse> customerResponses(){
        return customerService.getAll();
    }

    @PostMapping("/update")
    public void update(@PathVariable Integer id, @RequestBody CustomerRequest customerRequest){
        customerService.update(id,customerRequest);
    }

    @DeleteMapping("/delete")
    public void delete(@PathVariable Integer id) {
        customerService.delete(id);
    }

}
