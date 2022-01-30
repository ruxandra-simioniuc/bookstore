package pos.proiect.bookstore.service.interfaces;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pos.proiect.bookstore.model.User;

public interface UserService extends UserDetailsService {

    boolean authenticate(String username, String password);

    User saveUser(User user);

    User findUserByUsername(String username);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    boolean deleteUser(String username);

    boolean changePassword(String username, String newPassword);

    boolean changeRole(String username, String newRole);



}
