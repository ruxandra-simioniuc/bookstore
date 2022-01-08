package pos.proiect.bookstore.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pos.proiect.bookstore.model.Author;
import pos.proiect.bookstore.model.BookAuthor;
import pos.proiect.bookstore.repository.BookAuthorRepository;
import pos.proiect.bookstore.service.interfaces.BookAuthorServiceInterface;

import java.util.List;
@Service
public class BookAuthorService implements BookAuthorServiceInterface {

    BookAuthorRepository bookAuthorRepository;

    @Autowired
    public BookAuthorService(BookAuthorRepository bookAuthorRepository) {
        this.bookAuthorRepository = bookAuthorRepository;
    }


    @Override
    public List<BookAuthor> getAllEntries() {
        return bookAuthorRepository.findAll();
    }

    @Override
    public List<Author> getAuthorsByISBN(String ISBN) {
        return bookAuthorRepository.getAuthorsByISBN(ISBN);
    }


}
