package pos.proiect.bookstore.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pos.proiect.bookstore.model.User;
import pos.proiect.bookstore.repository.UserRepository;
import pos.proiect.bookstore.service.interfaces.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        //this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean authenticate(String username, String password) throws UsernameNotFoundException{
        System.out.println("user name: " + username + " password: " +password);

        try{
            UsernamePasswordAuthenticationToken tok = new UsernamePasswordAuthenticationToken(username, password);
            authenticationManager.authenticate(tok);
            return true;
        }catch(Exception e){
            return false;
        }
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
    public boolean deleteUser(String username) {
        User user = findUserByUsername(username);
        if(user!=null){
            userRepository.delete(user);
            return true;
        }
        return false;

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

   @Override
    public boolean changePassword(String username, String newPassword){
       User user = findUserByUsername(username);
       if(user!=null){
           user.setPassword(newPassword);
           userRepository.save(user);
           return true;
       }
       return false;
    }

    @Override
    public boolean changeRole(String username, String newRole){
        User user = findUserByUsername(username);
        if(user!=null){
            user.setRole(newRole);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
