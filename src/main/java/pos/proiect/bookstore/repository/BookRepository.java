package pos.proiect.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pos.proiect.bookstore.model.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {

    List<Book> findBooksByGenre(String genre);
    List<Book> findBooksByGenreAndYear(String genre, Integer year);
    List<Book> findBooksByYear(Integer year);

    /*@Modifying
    @Query("Update Book b set b.stock=?2 where b.isbn=?1")
    void updateStock(String ISBN, Integer quantity);*/


}
