package cholog;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Optional<Customer> findById(final Long id);

    List<Customer> findByLastName(final String lastName);

    List<Customer> findByLastNameIgnoreCase(final String bauer);

    List<Customer> findByLastNameOrderByFirstNameDesc(final String bauer);
}

