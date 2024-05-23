package practice.ex.book_shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.ex.book_shop.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
