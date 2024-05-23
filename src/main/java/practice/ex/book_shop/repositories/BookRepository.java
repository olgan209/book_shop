package practice.ex.book_shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.ex.book_shop.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Override
    Optional<Book> findById(Integer integer);

    List<Book> findByAuthor(String author);
    List<Book> findByTitle(String title);
}
