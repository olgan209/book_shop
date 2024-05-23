package practice.ex.book_shop.service;

import practice.ex.book_shop.dto.book.BookRequest;
import practice.ex.book_shop.dto.book.BookResponse;
import practice.ex.book_shop.entities.Book;
import practice.ex.book_shop.exception.BadCredentialsException;
import practice.ex.book_shop.exception.NotFoundException;
import practice.ex.book_shop.mapper.BookMapper;
import practice.ex.book_shop.repositories.BookRepository;
import practice.ex.book_shop.service.interfaces.BookServiceInterface;
import practice.ex.book_shop.user.Role;

import java.util.List;

public class BookService implements BookServiceInterface {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthenticationService authenticationService;
    public BookService(BookRepository bookRepository, BookMapper bookMapper) {}

    @Override
    public void addBook(BookRequest bookRequest, String token) {
        if(bookRepository.findById(bookRequest.getId()).isPresent())
            throw new NotFoundException("Book with id " + bookRequest.getId() + " already exists");
        if(!authenticationService.getUsernameFromToken(token).getRole().equals(Role.ADMIN))
            throw new BadCredentialsException("Only Admins can add a book");

        Book book = new Book();
        book.setAuthor(bookRequest.getAuthor());
        book.setTitle(bookRequest.getTitle());

        bookRepository.save(book);
    }

    @Override
    public void deleteBook(String token, Integer bookId) {

    }

    @Override
    public void dropBook(String token, Integer bookId) {

    }

    @Override
    public void updateBook(String token, BookRequest bookRequest, Integer bookId) {

    }

    @Override
    public List<BookResponse> getAllBooks() {
        return List.of();
    }

    @Override
    public List<BookResponse> getAllBooksByAuthor(String author) {
        return List.of();
    }

    @Override
    public List<BookResponse> getAllBooksByTitle(String title) {
        return List.of();
    }
}
