package practice.ex.book_shop.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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

@Service
@AllArgsConstructor
public class BookService implements BookServiceInterface {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthenticationService authenticationService;


    @Override
    public void addBook(BookRequest bookRequest, String token) {
        if(bookRepository.findById(bookRequest.getBookId()).isPresent())
            throw new NotFoundException("Book with id " + bookRequest.getBookId() + " already exists", HttpStatus.CONFLICT);
        if(!authenticationService.getUsernameFromToken(token).getRole().equals(Role.ADMIN))
            throw new BadCredentialsException("Only Admins can add a book");

        Book book = new Book();
        book.setAuthor(bookRequest.getAuthor());
        book.setTitle(bookRequest.getTitle());

        bookRepository.save(book);
    }

    @Override
    public void deleteBook(String token, Integer bookId) {
        if (!bookRepository.existsById(bookId))
            throw new NotFoundException("Book with id " + bookId + " does not exist", HttpStatus.NOT_FOUND);
        if (!authenticationService.getUsernameFromToken(token).getRole().equals(Role.ADMIN))
            throw new BadCredentialsException("Only Admins can delete a book");

        bookRepository.deleteById(bookId);
    }

    @Override
    public void dropBook(String token, Integer bookId) {
        if (!bookRepository.existsById(bookId))
            throw new NotFoundException("Book with id " + bookId + " does not exist", HttpStatus.NOT_FOUND);
        if (!authenticationService.getUsernameFromToken(token).getRole().equals(Role.ADMIN))
            throw new BadCredentialsException("Only Admins can drop a book");

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book with id " + bookId + " does not exist", HttpStatus.NOT_FOUND));

        bookRepository.delete(book);
    }

    @Override
    public void updateBook(String token, BookRequest bookRequest, Integer bookId) {
        if (!bookRepository.existsById(bookId))
            throw new NotFoundException("Book with id " + bookId + " does not exist", HttpStatus.NOT_FOUND);
        if (!authenticationService.getUsernameFromToken(token).getRole().equals(Role.ADMIN))
            throw new BadCredentialsException("Only Admins can update a book");

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book with id " + bookId + " does not exist", HttpStatus.NOT_FOUND));

        book.setAuthor(bookRequest.getAuthor());
        book.setTitle(bookRequest.getTitle());

        bookRepository.save(book);
    }

    @Override
    public List<BookResponse> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return bookMapper.toDto(books);
    }

    @Override
    public List<BookResponse> getAllBooksByAuthor(String author) {
        List<Book> books = bookRepository.findByAuthor(author);
        return bookMapper.toDto(books);
    }

    @Override
    public List<BookResponse> getAllBooksByTitle(String title) {
        List<Book> books = bookRepository.findByTitle(title);
        return bookMapper.toDto(books);
    }

}
