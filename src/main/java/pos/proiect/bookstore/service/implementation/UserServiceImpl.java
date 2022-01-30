package pos.proiect.bookstore.service.implementation;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pos.proiect.bookstore.model.User;
import pos.proiect.bookstore.repository.UserRepository;
import pos.proiect.bookstore.service.interfaces.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean authenticate(String username, String password) throws UsernameNotFoundException{
        User user = userRepository.findUserByUsernameAndAndPassword(username, password);
        if( user == null){
            return false;
        }
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findUserByUsername(username);

        if (user !=null){
            List<SimpleGrantedAuthority> roles = List.of(new SimpleGrantedAuthority(user.getRole()));

            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                    roles);
        }else{
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    @Override
    public User saveUser(User user) {
        if (findUserByUsername(user.getUsername()) != null) {
            System.out.println("exista deja un user cu acest username: " + user.getUsername());
            return null;
        } else {
            return userRepository.save(user);
        }
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

   /* @Override
    boolean changePassword(String username, String newPassword){
        return true;
    }

    @Override
    boolean changeRole(String username, String newRole){
        return true;
    }*/
}
