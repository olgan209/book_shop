package practice.ex.book_shop.service.interfaces;

import practice.ex.book_shop.dto.book.BookRequest;
import practice.ex.book_shop.dto.book.BookResponse;

import java.util.List;

public interface BookServiceInterface {
    void addBook(BookRequest bookRequest, String token);
    void deleteBook(String token, Integer bookId);
    void dropBook(String token, Integer bookId);
    void updateBook(String token, BookRequest bookRequest, Integer bookId);
    List<BookResponse> getAllBooks();
    List<BookResponse> getAllBooksByAuthor(String author);
    List<BookResponse> getAllBooksByTitle(String title);
}
