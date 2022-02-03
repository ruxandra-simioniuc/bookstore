package pos.proiect.bookstore.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pos.proiect.bookstore.generated.*;
import pos.proiect.bookstore.model.User;
import pos.proiect.bookstore.security.JwtTokenUtil;
import pos.proiect.bookstore.service.interfaces.JwtBlacklistService;
import pos.proiect.bookstore.service.interfaces.UserService;

//import org.springframework.security.authentication.AuthenticationManager;

@Endpoint
public class AuthEndpoint {
    private static final String NAMESPACE_URI = "http://bookstore.pos.proiect/Auth";


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserService userService;

    @Autowired
    JwtBlacklistService jwtBlacklistService;

    @Autowired
    PasswordEncoder passwordEncoder;

   /* @Autowired
    public AuthEndpoint(UserService userService) {
        this.userService = userService;
    }*/

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "loginRequest")
    @ResponsePayload
    public JwtResponse login(@RequestPayload LoginRequest request) throws Exception {
        JwtResponse response = new JwtResponse();
        String encodedPass =passwordEncoder.encode(request.getPassword());

        if(userService.authenticate(request.getUsername(), request.getPassword())) {

            UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
            String token = jwtTokenUtil.generateToken(userDetails);
            System.out.println(request.getUsername() + " " + request.getPassword());
            System.out.println("jwt: " + token);
            response.setJwt(token);
        }else{
            System.out.println("errrrr");
            response.setJwt("invalid request");
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "registerRequest")
    @ResponsePayload
    public GeneralResponse register(@RequestPayload RegisterRequest request) throws Exception {

        String encodedPass =passwordEncoder.encode(request.getPassword());


        String token = request.getJwt();
        //String id = token.split(" ", 0)[1];
        //System.out.println("id: "+id);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        System.out.println("username from token: "+username);

        User requestingUser = userService.findUserByUsername(jwtTokenUtil.getUsernameFromToken(token));

        GeneralResponse result = new GeneralResponse();
        //if(requestingUser.getRole().equals("admin")) {

            System.out.println("e admin e ok");
            User newUser = new User();
            newUser.setUsername(request.getUsername());
            newUser.setPassword(encodedPass);
            newUser.setRole(request.getRole());

            User response = userService.saveUser(newUser);
            if (response != null)
                result.setResponse("user registered " + response.getUsername());
            else result.setResponse("problem registering");
//        }else {
//            result.setResponse("not authorized");
//        }
        return result;

    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "validateToken")
    @ResponsePayload
    public ValidationResponse validateToken(@RequestPayload ValidateToken request) throws Exception {
        ValidationResponse validationResponse = new ValidationResponse();


        if(jwtBlacklistService.isExpired(request.getJwt())){
            System.out.println("exipred token");
            validationResponse.setRole("null");
            validationResponse.setSub(null);
            jwtBlacklistService.addJWTtoBlacklist(request.getJwt());
        }else{
            User user = userService.findUserByUsername(jwtTokenUtil.getUsernameFromToken(request.getJwt()));
            validationResponse.setRole(user.getRole());
            validationResponse.setSub(user.getId());
        }
        return validationResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "destroyToken")
    @ResponsePayload
    public GeneralResponse destroyToken(@RequestPayload DestroyToken request) throws Exception {
        GeneralResponse response = new GeneralResponse();

        if(jwtBlacklistService.addJWTtoBlacklist(request.getJwt())){
            response.setResponse("Success");
        }else{
            response.setResponse("Failure");
        }

//        if(jwtBlacklistService.isExpired(request.getJwt())){
//            response.setResponse("Success");
//        }else{
//            response.setResponse("Failure");
//        }
//        //response.setResponse("Success");
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "logoutRequest")
    @ResponsePayload
    public GeneralResponse logout(@RequestPayload LogoutRequest request) throws Exception {
        DestroyToken destroyToken = new DestroyToken();
        destroyToken.setJwt(request.getJwt());

        return destroyToken( destroyToken);
    }


}
