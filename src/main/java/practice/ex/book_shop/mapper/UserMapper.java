package practice.ex.book_shop.mapper;

import org.springframework.stereotype.Component;
import practice.ex.book_shop.dto.user.UserResponse;
import practice.ex.book_shop.entities.User;
import practice.ex.book_shop.mapper.UserMap;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper implements UserMap{

    @Override
    public UserResponse toDto(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setAge(user.getAge());
        userResponse.setName(user.getFirstname());
        return userResponse;
    }

    @Override
    public List<UserResponse> toDto(List<User> all) {
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user: all){
            userResponses.add(toDto(user));
        }
        return userResponses;
    }
}
