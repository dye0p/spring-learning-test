package cholog;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

@DirtiesContext()
@DataJpaTest
public class ManyToOneTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Test
    void uniDirection() {
        Publisher publisher = new Publisher("출판사");
        entityManager.persist(publisher);

        Book book = new Book("책", publisher);
        entityManager.persist(book);

        Book persistBook = entityManager.find(Book.class, book.getId());

        entityManager.flush();

        assertThat(persistBook).isNotNull();
        assertThat(persistBook.getPublisher()).isNotNull();
    }

    @Test
    void biDirection() {
        Publisher publisher = new Publisher("출판사");
        entityManager.persist(publisher);

        Book book = new Book("책", publisher);
        entityManager.persist(book);

        publisher.addBook(book); // 실제 필드에는 반영되지 않음 (연관관계의 주인이 아니기 때문에 더티체킹이 안됨

        entityManager.flush();
        entityManager.clear();

        System.out.println("bookRepo.findById");
        bookRepository.findById(book.getId());

        System.out.println("pubRepo.findById");
        publisherRepository.findById(publisher.getId());
        System.out.println("getBooks");
//        assertThat(persistPublisher.getBooks()).isNotEmpty();
    }

    @Test
    void findByIdForBook() {
        Publisher publisher = new Publisher("출판사");
        entityManager.persist(publisher);

        Book book = new Book("책", publisher);
        entityManager.persist(book);

        entityManager.flush();
        entityManager.clear();

        /*
        fetch 전략에 따라서 조회 쿼리가 달라짐
        Lazy = 지연 로딩(필요할때 조회 쿼리 날림)
        EAGER = 즉시 로딩(연관 관계의 주인 쪽에서 한번에 join 쿼리 날림)
         */
        Optional<Book> persistBook = bookRepository.findById(book.getId());
        assertThat(persistBook).isPresent();
        assertThat(persistBook.get().getPublisher()).isNotNull();
    }

    @Test
    void findByIdForPublisher() {
        Publisher publisher = new Publisher("출판사");
        entityManager.persist(publisher);

        Book book = new Book("책", publisher);
        entityManager.persist(book);

        entityManager.flush();
        entityManager.clear(); // 영속성 컨텍스트 비움 (= 1차 캐시 조회 안됨)

        Optional<Publisher> persistPublisher = publisherRepository.findById(publisher.getId());
        assertThat(persistPublisher).isPresent();
        assertThat(persistPublisher.get().getBooks()).isNotNull();
    }
}
