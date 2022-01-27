package pos.proiect.bookstore.service.interfaces;

import pos.proiect.bookstore.model.User;

public interface UserService {

    String authenticate(String username, String password);

    User getUserByUsername(String username);

    User saveUser(User user);

    //boolean changePassword(String username, String newPassword);

    //boolean changeRole(String username, String newRole);



}
