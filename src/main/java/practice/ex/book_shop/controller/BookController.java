package practice.ex.book_shop.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.ex.book_shop.dto.book.BookRequest;
import practice.ex.book_shop.dto.book.BookResponse;
import practice.ex.book_shop.entities.Book;
import practice.ex.book_shop.mapper.BookMap;
import practice.ex.book_shop.service.BookService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<String> addBook(@RequestBody BookRequest bookRequest, @RequestHeader("Authorization") String token) {
        bookService.addBook(bookRequest, token);
        return ResponseEntity.status(HttpStatus.CREATED).body("Book added successfully");
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer bookId, @RequestHeader("Authorization") String token) {
        bookService.deleteBook(token, bookId);
        return ResponseEntity.status(HttpStatus.OK).body("Book deleted successfully");
    }

    @DeleteMapping("/drop/{bookId}")
    public ResponseEntity<String> dropBook(@PathVariable Integer bookId, @RequestHeader("Authorization") String token) {
        bookService.dropBook(token, bookId);
        return ResponseEntity.status(HttpStatus.OK).body("Book dropped successfully");
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<String> updateBook(@PathVariable Integer bookId, @RequestBody BookRequest bookRequest, @RequestHeader("Authorization") String token) {
        bookService.updateBook(token, bookRequest, bookId);
        return ResponseEntity.status(HttpStatus.OK).body("Book updated successfully");
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<BookResponse> books = bookService.getAllBooks();
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<BookResponse>> getAllBooksByAuthor(@PathVariable String author) {
        List<BookResponse> books = bookService.getAllBooksByAuthor(author);
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<BookResponse>> getAllBooksByTitle(@PathVariable String title) {
        List<BookResponse> books = bookService.getAllBooksByTitle(title);
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

}
