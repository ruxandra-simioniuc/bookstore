package pos.proiect.bookstore.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import org.springframework.ws.soap.client.core.SoapActionCallback;
import pos.proiect.bookstore.generated.JwtResponse;
import pos.proiect.bookstore.generated.LoginRequest;
import pos.proiect.bookstore.security.JwtTokenUtil;
import pos.proiect.bookstore.service.interfaces.UserService;


public class UserClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(UserClient.class);

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public JwtResponse login(String username, String password){

        LoginRequest request = new LoginRequest();
        request.setPassword(password);
        request.setUsername(username);

        log.info("Requesting login for " + username);

        JwtResponse response = (JwtResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/auth", request,
                        new SoapActionCallback("http://bookstore.pos.proiect/Auth/LoginRequest"
                        ));
        return response;

    }

}
