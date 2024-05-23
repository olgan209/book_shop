package practice.ex.book_shop.dto.book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookResponse {
    private String title;
    private String author;
    private Integer id;
}
