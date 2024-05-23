package practice.ex.book_shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.ex.book_shop.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
