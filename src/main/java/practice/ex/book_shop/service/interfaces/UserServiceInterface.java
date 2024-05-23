package practice.ex.book_shop.service.interfaces;

import practice.ex.book_shop.dto.user.UserRequest;
import practice.ex.book_shop.dto.user.UserResponse;
import practice.ex.book_shop.entities.User;

import java.util.List;

public interface UserServiceInterface {
    UserResponse getById(Integer id, String token);

    User getUsernameFromToken(String token);

    void deleteById(Integer id);

    void updateById(Integer id, UserRequest userRequest);

    List<UserResponse> getAll();
}
