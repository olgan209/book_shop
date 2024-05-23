package practice.ex.book_shop.mapper;

import practice.ex.book_shop.dto.book.BookResponse;
import practice.ex.book_shop.entities.Book;

import java.util.List;

public interface BookMap {
    BookResponse toDto(Book book);

    List<BookResponse> toDto(List<Book> all);
}
