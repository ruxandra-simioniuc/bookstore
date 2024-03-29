package pos.proiect.bookstore.service.interfaces;

import pos.proiect.bookstore.model.Author;
import pos.proiect.bookstore.model.BookAuthor;

import java.util.List;

public interface BookAuthorService {
    List<BookAuthor> getAllEntries();
    List<Author> getAuthorsByISBN(String ISBN);
}
