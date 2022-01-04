package pos.proiect.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pos.proiect.bookstore.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
