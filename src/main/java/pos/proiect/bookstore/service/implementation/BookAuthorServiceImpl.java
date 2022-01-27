package pos.proiect.bookstore.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pos.proiect.bookstore.model.Author;
import pos.proiect.bookstore.model.BookAuthor;
import pos.proiect.bookstore.repository.BookAuthorRepository;
import pos.proiect.bookstore.service.interfaces.BookAuthorService;

import java.util.List;
@Service
public class BookAuthorServiceImpl implements BookAuthorService {

    BookAuthorRepository bookAuthorRepository;

    @Autowired
    public BookAuthorServiceImpl(BookAuthorRepository bookAuthorRepository) {
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
