package pos.proiect.bookstore.controller;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pos.proiect.bookstore.model.Author;
import pos.proiect.bookstore.service.interfaces.AuthorServiceInterface;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/bookcollection/authors")
public class AuthorController {


    private AuthorServiceInterface authorService;

    public AuthorController(AuthorServiceInterface authorService) {
        this.authorService = authorService;
    }

    ///api/bookcollection/authors?name=...&match=exact
    @GetMapping
    public List<Author> getAuthors(@RequestParam(name="name")Optional<String> name, @RequestParam(name="match") Optional<String> match){

        if(name.isPresent()) {
            if (match.isEmpty())
                return authorService.getAuthorByName(name.get());
            else if (match.get().equals("exact"))
                return authorService.getAuthorByNameMatchExact(name.get());
        }

        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable("id") String id){
        return new ResponseEntity<Author>(authorService.getAuthorByID(Integer.parseInt(id)), HttpStatus.OK);
    }

}
