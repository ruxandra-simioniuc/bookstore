package pos.proiect.bookstore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pos.proiect.bookstore.model.Book;

import java.util.List;

@RestController
@RequestMapping(path="api/bookcollection/books")
public class BookController {
/*
    @GetMapping
    public Book getBooks(){
        return null;
    }

    //@GetMapping("/{isbn}")

    //http://localhost:8080/api/bookcollection/books?genre=..
    @GetMapping
    public List<Book> bookGenreQuery(@RequestParam(name="genre") String genre){
        return null;
    }
*/
}
