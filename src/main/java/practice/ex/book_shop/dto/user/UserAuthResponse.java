package practice.ex.book_shop.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthResponse {
    private String token;
    private String email;
    private String role;
}
