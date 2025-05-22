package cholog;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class BookAuthor {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", unique = true)
    private Book book; //연관관계 주인

    @ManyToOne
    @JoinColumn(name = "author_id", unique = true)
    private Author author; //연관관계 주인

    public BookAuthor() {
    }

    public BookAuthor(final Book book, final Author author) {
        this.book = book;
        this.author = author;
    }
}
