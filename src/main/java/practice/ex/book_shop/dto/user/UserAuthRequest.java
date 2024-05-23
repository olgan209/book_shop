package practice.ex.book_shop.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthRequest {
    private String email;
    private String password;
    private String role;
}
