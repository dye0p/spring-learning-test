package cholog;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY) //연관관계 주인
    private Publisher publisher;

    @OneToMany(mappedBy = "book")
    private Set<BookAuthor> bookAuthors;

    public Book() {

    }

    public Book(final String name, final Publisher publisher) {
        this.name = name;
        this.publisher = publisher;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Publisher getPublisher() {
        return this.publisher;
    }

    public Set<BookAuthor> getAuthors() {
        return this.bookAuthors;
    }
}
