package practice.ex.book_shop.entities;

import jakarta.persistence.*;
import practice.ex.book_shop.user.Type;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="_booksTable")
public class Book {

    @Id
    @GeneratedValue
    private Integer bookId;
    private String title;
    private String author;
    @Enumerated(EnumType.STRING)
    private Type BookType;

//    @ManyToOne
//    private Customer customer;
}
