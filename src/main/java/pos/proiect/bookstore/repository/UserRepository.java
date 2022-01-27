package pos.proiect.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pos.proiect.bookstore.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    //User findUserByUsername(String username);
    User findUserByUsernameAndAndPassword(String username, String password);
    User findUserByUsername(String username);
}
