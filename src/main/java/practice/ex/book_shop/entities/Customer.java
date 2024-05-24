package practice.ex.book_shop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name="_customerTable")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;

//    @OneToMany(mappedBy = "customer")
//    List<Book> books;
//
//    @OneToOne(mappedBy = "user")
//    private User user;

}
