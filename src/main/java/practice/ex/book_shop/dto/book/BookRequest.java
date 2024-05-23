package practice.ex.book_shop.dto.book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {
    public Integer getId;
    private String title;
    private String author;
}
