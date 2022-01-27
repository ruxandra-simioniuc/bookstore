package pos.proiect.bookstore.service.implementation;

import org.springframework.stereotype.Service;
import pos.proiect.bookstore.model.User;
import pos.proiect.bookstore.repository.UserRepository;
import pos.proiect.bookstore.service.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String authenticate(String username, String password) {
        User user = userRepository.findUserByUsernameAndAndPassword(username, password);
        if( user == null){
            return "can't find user";
        }
        return "user found";
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        return user;
    }

    @Override
    public User saveUser(User user) {
        if(getUserByUsername(user.getUsername()) != null){
            System.out.println("exista deja un user cu acest username: " +user.getUsername());
            return null;
        }else{
            return userRepository.save(user);
        }
    }
}
