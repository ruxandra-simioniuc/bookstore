package pos.proiect.bookstore.service.interfaces;

import pos.proiect.bookstore.model.Book;
import pos.proiect.bookstore.model.BookInterface;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();
    Book getBookByISBN(String ISBN);
    List<Book> getBooksByGenre(String genre);
    List<Book> getBooksByYear(Integer year);
    List<Book> getBooksByYearAndGenre(String genre, Integer year);
    BookInterface getBookByISBNVerboseFalse(String ISBN);
    List<Book> getBooksPagination(Integer pageNo, Integer items_per_page);
    boolean stockOk(String ISBN, Integer quantity);
    void decreaseStock(String ISBN, Integer quantity);
}
