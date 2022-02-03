package pos.proiect.bookstore.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pos.proiect.bookstore.model.Book;
import pos.proiect.bookstore.model.BookInterface;
import pos.proiect.bookstore.model.User;
import pos.proiect.bookstore.security.JwtTokenUtil;
import pos.proiect.bookstore.service.interfaces.BookService;
import pos.proiect.bookstore.service.interfaces.UserService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/bookcollection/books")
public class BookController {

    private BookService bookService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserService userService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //get all books
    //or filter
    //http://localhost:8080/api/bookcollection/books?genre=dragoste&year=1847
    @GetMapping
    public ResponseEntity<List<Book>> getBooks(@RequestParam Map<String, String> allParams){
        if(allParams.containsKey("page")){
            int items = 5;
            if(allParams.containsKey("items_per_page")){
                items = Integer.parseInt(allParams.get("items_per_page"));
                return new ResponseEntity<List<Book>>(bookService.getBooksPagination(Integer.parseInt(allParams.get("page")), items), HttpStatus.OK);
            }
        }

        if(allParams.containsKey("genre")){
            if(allParams.containsKey("year")){
                return new ResponseEntity<List<Book>>(bookService.getBooksByYearAndGenre(allParams.get("genre"), Integer.parseInt(allParams.get("year"))), HttpStatus.OK);
            }
            else return new ResponseEntity<List<Book>>(bookService.getBooksByGenre(allParams.get("genre")), HttpStatus.OK);
        }else if(allParams.containsKey("year")){
            return new ResponseEntity<List<Book>>(bookService.getBooksByYear(Integer.parseInt(allParams.get("year"))), HttpStatus.OK);
        }
        else return new ResponseEntity<List<Book>>(bookService.getAllBooks(), HttpStatus.OK);
    }


/*    public ResponseEntity<List<Book>> getBooks(@RequestParam(name="genre") Optional<String> genre, @RequestParam(name="year") Optional<Integer> year, @RequestParam(name="page", defaultValue = "0") Optional<Integer> page, @RequestParam(defaultValue = "5", name="items-per-page") Optional<Integer> items_per_page){

        *//*
        if(page.isPresent()) {
            return new ResponseEntity<List<Book>>(bookService.getBooksPagination(page.get(), items_per_page.get()), HttpStatus.OK);
        }

         *//*

        if(genre.isEmpty() && year.isEmpty())
            return new ResponseEntity<List<Book>>(bookService.getAllBooks(), HttpStatus.OK);
        else{
            if(genre.isPresent()) {
                if (year.isEmpty())
                    return new ResponseEntity<List<Book>>(bookService.getBooksByGenre(genre.get()), HttpStatus.OK);
                else
                    return new ResponseEntity<List<Book>>(bookService.getBooksByYearAndGenre(genre.get(), year.get()), HttpStatus.OK);
            }
            else return new ResponseEntity<List<Book>>(bookService.getBooksByYear(year.get()), HttpStatus.OK);

        }
    }*/

    //http://localhost:8080/api/bookcollection/books/{isbn}?verbose=false
    @GetMapping("/{isbn}")
    public ResponseEntity<BookInterface> getBookByISBN(@PathVariable("isbn") String ISBN, @RequestParam(name="verbose") Optional<Boolean> verbose){
        if(verbose.isPresent() && !verbose.get())
            return new ResponseEntity<BookInterface>(bookService.getBookByISBNVerboseFalse(ISBN), HttpStatus.OK);
        else
            return new ResponseEntity<BookInterface>(bookService.getBookByISBN(ISBN), HttpStatus.OK);
    }

    @PostMapping("/{isbn}/{quantity}")
    public ResponseEntity<String> checkOrderAndUpdateStock(@PathVariable("isbn") String isbn, @PathVariable("quantity") Integer quantity){
        if(bookService.stockOk(isbn, quantity)){
            bookService.decreaseStock(isbn, quantity);
            return new ResponseEntity<String>("Stock ok for "+bookService.getBookByISBN(isbn).getTitle(), HttpStatus.OK);
        }
        return new ResponseEntity<String>("stock too low", HttpStatus.CONFLICT);
    }

    @PostMapping("/modify/{isbn}")
    public ResponseEntity<String> modifyBook(@PathVariable("isbn") String isbn, @RequestBody Book alt, @RequestHeader("Authorization") String jwt){

        String token = jwt.replace("Bearer:", "");

        User user = userService.findUserByUsername(jwtTokenUtil.getUsernameFromToken(token));
        String role = user.getRole();

        if(role.equals("manager")){
            Book book = bookService.getBookByISBN(isbn);
            BeanUtils.copyProperties(alt, book, "isbn");
            bookService.addBook(book);

            return new ResponseEntity<String>("modified", HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("error", HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/delete/{isbn}")
    public ResponseEntity<String> modifyBook(@PathVariable("isbn") String isbn, @RequestHeader("Authorization") String jwt){

        String token = jwt.replace("Bearer:", "");

        User user = userService.findUserByUsername(jwtTokenUtil.getUsernameFromToken(token));
        String role = user.getRole();

        if(role.equals("manager")){
            bookService.deleteBook(isbn);

            return new ResponseEntity<String>("deleted", HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("error", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> modifyBook( @RequestBody Book newBook, @RequestHeader("Authorization") String jwt){

        String token = jwt.replace("Bearer:", "");

        User user = userService.findUserByUsername(jwtTokenUtil.getUsernameFromToken(token));
        String role = user.getRole();

        if(role.equals("manager")){

            bookService.addBook(newBook);

            return new ResponseEntity<String>("new book added", HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("error", HttpStatus.UNAUTHORIZED);
        }
    }


}
