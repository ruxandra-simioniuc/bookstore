package pos.proiect.bookstore.service.interfaces;

import pos.proiect.bookstore.model.Book;

import java.util.List;

public interface BookServiceInterface {

    List<Book> getAllBooks();
    Book getBookByISBN(String ISBN);
    List<Book> getBooksByGenre(String genre);
    List<Book> getBooksByYear(Integer year);
    List<Book> getBooksByYearAndGenre(String genre, Integer year);

}
