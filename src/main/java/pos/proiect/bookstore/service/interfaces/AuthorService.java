package pos.proiect.bookstore.service.interfaces;

import pos.proiect.bookstore.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();
    Author getAuthorByID(Integer id);
    List<Author> getAuthorByName(String name);
    List<Author> getAuthorByNameMatchExact(String name);
}
