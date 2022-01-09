package pos.proiect.bookstore.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pos.proiect.bookstore.exception.ResourceNotFoundException;
import pos.proiect.bookstore.model.Book;
import pos.proiect.bookstore.model.BookInterface;
import pos.proiect.bookstore.model.BookVerboseFalse;
import pos.proiect.bookstore.repository.BookRepository;
import pos.proiect.bookstore.service.interfaces.BookServiceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService implements BookServiceInterface {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookByISBN(String ISBN) {
        Optional<Book> book =  bookRepository.findById(ISBN);

        if(book.isPresent())
            return book.get();
        else {
            throw new ResourceNotFoundException("Book", "isbn", ISBN);
        }
    }

    @Override
    public List<Book> getBooksByGenre(String genre) {
        return bookRepository.findBooksByGenre(genre);
        //return bookRepository.findAll().stream().filter(b->b.getGenre().equals(genre)).collect(Collectors.toList());
    }

    @Override
    public List<Book> getBooksByYear(Integer year) {
        return bookRepository.findBooksByYear(year);
        //return bookRepository.findAll().stream().filter(b-> b.getYear().equals(year)).collect(Collectors.toList());
    }

    @Override
    public List<Book> getBooksByYearAndGenre(String genre, Integer year) {
        return bookRepository.findBooksByGenreAndYear(genre, year);
        //return this.getBooksByGenre(genre).stream().filter(this.getBooksByYear(year)::contains).collect(Collectors.toList());
    }

    @Override
    public BookInterface getBookByISBNVerboseFalse(String ISBN) {
        Optional<Book> book = bookRepository.findById(ISBN);
        if(book.isPresent())
            return new BookVerboseFalse(book.get().getIsbn(), book.get().getTitle(), book.get().getGenre());
        else
            throw new ResourceNotFoundException("Book", "ISBN", ISBN);
    }

    @Override
    public List<Book> getBooksPagination(Integer pageNo, Integer items_per_page) {
        Pageable paging = PageRequest.of(pageNo, items_per_page);
        Page<Book> pagedResult = bookRepository.findAll(paging);

        if(pagedResult.hasContent()){
            return pagedResult.getContent();
        }else{
            return  new ArrayList<Book>();
        }


    }

    @Override
    public boolean stockOk(String ISBN, Integer quantity) {
        Optional<Book> bk = bookRepository.findById(ISBN);
        if(bk.isPresent()){
            if(bk.get().getStock() >= quantity)
                return true;
        }
        return false;
    }

    @Override
    public void decreaseStock(String ISBN, Integer quantity) {
        Optional<Book> bk = bookRepository.findById(ISBN);
        if(bk.isPresent()) {
            bk.get().setStock(bk.get().getStock() - quantity);

            bookRepository.save(bk.get());
        }
    }


}
