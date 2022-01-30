package pos.proiect.bookstore.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pos.proiect.bookstore.generated.LoginRequest;
import pos.proiect.bookstore.generated.GeneralResponse;
import pos.proiect.bookstore.generated.LogoutRequest;
import pos.proiect.bookstore.generated.RegisterRequest;
import pos.proiect.bookstore.model.User;
import pos.proiect.bookstore.security.JwtTokenUtil;
import pos.proiect.bookstore.service.interfaces.UserService;
//import org.springframework.security.authentication.AuthenticationManager;

@Endpoint
public class AuthEndpoint {
    private static final String NAMESPACE_URI = "http://bookstore.pos.proiect/Auth";


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    UserService userService;

    @Autowired
    public AuthEndpoint(UserService userService) {
        this.userService = userService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "loginRequest")
    @ResponsePayload
    public GeneralResponse login(@RequestPayload LoginRequest request) throws Exception {
        GeneralResponse response = new GeneralResponse();

        if(userService.authenticate(request.getUsername(), request.getPassword())) {

            UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
            String token = jwtTokenUtil.generateToken(userDetails);
            System.out.println(request.getUsername() + " " + request.getPassword());
            System.out.println("jwt: " + token);
            response.setResponse("Authenticated succesfully");
        }else{
            System.out.println("errrrr");
            response.setResponse("Authentication failed");
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "registerRequest")
    @ResponsePayload
    public GeneralResponse register(@RequestPayload RegisterRequest request) throws Exception {

        //request.setPassword(passwordEncoder.encode(request.getPassword()));


        String token = request.getJwt();
        //String id = token.split(" ", 0)[1];
        //System.out.println("id: "+id);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        System.out.println("username from token: "+username);

        User requestingUser = userService.findUserByUsername(jwtTokenUtil.getUsernameFromToken(token));

        GeneralResponse result = new GeneralResponse();
        if(requestingUser.getRole().equals("admin")) {

            System.out.println("e admin e ok");
            User newUser = new User();
            newUser.setUsername(request.getUsername());
            newUser.setPassword(request.getPassword());
            newUser.setRole(request.getRole());

            User response = userService.saveUser(newUser);
            if (response != null)
                result.setResponse("user registered " + response.getUsername());
            else result.setResponse("problem registering");
        }else {
            result.setResponse("not authorized");
        }
        return result;

    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "logoutRequest")
    @ResponsePayload
    public GeneralResponse logout(@RequestPayload LogoutRequest request) throws Exception {
        return null;
    }


}
