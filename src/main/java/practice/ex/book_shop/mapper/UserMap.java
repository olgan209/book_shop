package practice.ex.book_shop.mapper;

import practice.ex.book_shop.dto.user.UserResponse;
import practice.ex.book_shop.entities.User;

import java.util.List;

public interface UserMap {
    UserResponse toDto(User user);
    List<UserResponse> toDto(List<User> all);
}
