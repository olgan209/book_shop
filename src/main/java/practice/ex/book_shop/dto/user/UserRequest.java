package practice.ex.book_shop.dto.user;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserRequest {
    private String email;
    private String password;
}
