package practice.ex.book_shop.mapper;

import org.springframework.stereotype.Component;
import practice.ex.book_shop.dto.book.BookResponse;
import practice.ex.book_shop.entities.Book;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookMapper implements BookMap {
    @Override
    public BookResponse toDto(Book book) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setBookId(book.getBookId());
        bookResponse.setAuthor(book.getAuthor());
        bookResponse.setTitle(book.getTitle());

        return bookResponse;
    }

    @Override
    public List<BookResponse> toDto(List<Book> all) {
        List<BookResponse> bookResponses = new ArrayList<>();
        for (Book book : all) {
            bookResponses.add(toDto(book));
        }
        return bookResponses;
    }
}
