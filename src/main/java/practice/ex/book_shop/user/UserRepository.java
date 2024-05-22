package practice.ex.book_shop.user;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(String email);
}
