package pos.proiect.bookstore.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pos.proiect.bookstore.generated.LoginRequest;
import pos.proiect.bookstore.generated.LoginResponse;
import pos.proiect.bookstore.generated.RegisterRequest;
import pos.proiect.bookstore.generated.RegisterResponse;
import pos.proiect.bookstore.model.User;
import pos.proiect.bookstore.service.interfaces.UserService;
//import org.springframework.security.authentication.AuthenticationManager;

@Endpoint
public class AuthEndpoint {
    private static final String NAMESPACE_URI = "http://bookstore.pos.proiect/Auth";


//    @Autowired
//    private AuthenticationManager authenticationManager;

    UserService userService;

    @Autowired
    public AuthEndpoint(UserService userService) {
        this.userService = userService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "loginRequest")
    @ResponsePayload
    public LoginResponse login(@RequestPayload LoginRequest request) {
        LoginResponse response = new LoginResponse();
        response.setResponse(userService.authenticate(request.getUsername(), request.getPassword()));
        //response.setResponse(request.getUsername() + request.getUsername());
        //response.setResponse(userService.getUserByUsername(request.getUsername()).getUsername());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "registerRequest")
    @ResponsePayload
    public RegisterResponse register(@RequestPayload RegisterRequest request) throws Exception {
        // Hash pentru parola primita
        //input.setPass(passwordEncoder.encode(input.getPass()));
        RegisterResponse result = new RegisterResponse();

        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(request.getPassword());
        newUser.setRole(request.getRole());

        User response = userService.saveUser(newUser);
        if(response != null)
            result.setResponse("user registered " +response.getUsername() );
        else result.setResponse("problem registering");

        return result;

    }


}
