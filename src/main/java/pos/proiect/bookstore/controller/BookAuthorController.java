package pos.proiect.bookstore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pos.proiect.bookstore.model.Author;
import pos.proiect.bookstore.service.implementation.BookAuthorServiceImpl;
import pos.proiect.bookstore.service.interfaces.BookAuthorService;

import java.util.List;

@RestController
@RequestMapping("api/bookcollection/books")
public class BookAuthorController {

    private BookAuthorService bookAuthorService;

    public BookAuthorController(BookAuthorService bookAuthorService) {
        this.bookAuthorService = bookAuthorService;
    }

    @GetMapping("/{isbn}/authors")
    public ResponseEntity<List<Author>> getAuthorsByISBN(@PathVariable("isbn") String ISBN){
        return new ResponseEntity<List<Author>>(bookAuthorService.getAuthorsByISBN(ISBN), HttpStatus.OK);
    }


}
