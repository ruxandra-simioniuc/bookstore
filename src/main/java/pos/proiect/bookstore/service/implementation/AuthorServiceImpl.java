package pos.proiect.bookstore.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pos.proiect.bookstore.model.Author;
import pos.proiect.bookstore.repository.AuthorRepository;
import pos.proiect.bookstore.service.interfaces.AuthorService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorByID(Integer id) {
        return authorRepository.findById(id).get();
    }

    @Override
    public List<Author> getAuthorByName(String name) {
        return authorRepository.findAll().stream().filter(a -> a.getFirst_name().contains(name) || a.getLast_name().contains(name)).collect(Collectors.toList());
    }

    @Override
    public List<Author> getAuthorByNameMatchExact(String name) {
        return authorRepository.findAll().stream().filter(a -> a.getLast_name().equals(name)).collect(Collectors.toList());
    }
}
