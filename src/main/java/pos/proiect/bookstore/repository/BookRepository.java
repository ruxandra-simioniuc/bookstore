package pos.proiect.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pos.proiect.bookstore.model.Book;

public interface BookRepository extends JpaRepository<Book, String> {



}
