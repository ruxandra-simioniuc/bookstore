package pos.proiect.bookstore.controller;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pos.proiect.bookstore.model.Author;
import pos.proiect.bookstore.model.Book;
import pos.proiect.bookstore.service.interfaces.BookServiceInterface;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/bookcollection/books")
public class BookController {

    private BookServiceInterface bookService;

    public BookController(BookServiceInterface bookService) {
        this.bookService = bookService;
    }

    //get all books
    //or filter
    //http://localhost:8080/api/bookcollection/books?genre=dragoste&year=1847
    @GetMapping
    public List<Book> getBooks(@RequestParam(name="genre") Optional<String> genre, @RequestParam(name="year") Optional<Integer> year){

        if(genre.isEmpty() && year.isEmpty())
            return bookService.getAllBooks();
        else{
            if(genre.isPresent()) {
                if (year.isEmpty())
                    return bookService.getBooksByGenre(genre.get());
                else
                    return bookService.getBooksByYearAndGenre(genre.get(), year.get());
            }
            else return bookService.getBooksByYear(year.get());

        }
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBookByISBN(@PathVariable("isbn") String ISBN){
        return new ResponseEntity<Book>(bookService.getBookByISBN(ISBN), HttpStatus.OK);
    }


}
