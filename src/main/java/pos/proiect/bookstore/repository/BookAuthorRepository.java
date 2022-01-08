package pos.proiect.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pos.proiect.bookstore.model.Author;
import pos.proiect.bookstore.model.BookAuthor;
import pos.proiect.bookstore.model.BookAuthorPK;

import java.util.List;

public interface BookAuthorRepository extends JpaRepository<BookAuthor, BookAuthorPK> {
    @Query("SELECT A FROM BookAuthor C, Author A WHERE C.bookAuthorPK.isbn=:ISBN AND C.bookAuthorPK.id_author = A.id")
    List<Author> getAuthorsByISBN(String ISBN);
}
