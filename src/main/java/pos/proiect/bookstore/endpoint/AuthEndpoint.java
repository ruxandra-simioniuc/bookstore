package pos.proiect.bookstore.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pos.proiect.bookstore.generated.LoginRequest;
import pos.proiect.bookstore.generated.LoginResponse;

@Endpoint
public class AuthEndpoint {
    private static final String NAMESPACE_URI = "http://bookstore.pos.proiect/Auth";

   // private userRepository userRepository;


    /*@Autowired
    public AuthEndpoint(userRepository userRepository) {
        this.userRepository = userRepository;
    }*/

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "loginRequest")
    @ResponsePayload
    public LoginResponse login(@RequestPayload LoginRequest request) {
        LoginResponse response = new LoginResponse();
        response.setResponse(userService.authenticate(request.getUsername(), request.getPassword()));

        return response;
    }
}
