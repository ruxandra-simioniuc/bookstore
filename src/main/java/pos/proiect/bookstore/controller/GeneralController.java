package pos.proiect.bookstore.controller;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/bookcollection")
public class GeneralController {
    @RequestMapping(value = "", method = RequestMethod.OPTIONS)
    ResponseEntity<?> collectionOptions1(){
        return ResponseEntity
                .ok()
                .allow(HttpMethod.GET, HttpMethod.OPTIONS)
                .build();
    }

    @RequestMapping(value = "/books", method = RequestMethod.OPTIONS)
    ResponseEntity<?> collectionOptions2(){
        return ResponseEntity
                .ok()
                .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.OPTIONS)
                .build();
    }

    @RequestMapping(value = "/books/{isbn}", method = RequestMethod.OPTIONS)
    ResponseEntity<?> singularOptions2(){
        return ResponseEntity
                .ok()
                .allow(HttpMethod.GET,HttpMethod.DELETE, HttpMethod.PUT, HttpMethod.OPTIONS)
                .build();
    }

    @RequestMapping(value = "/authors", method = RequestMethod.OPTIONS)
    ResponseEntity<?> collectionOptions3(){
        return ResponseEntity
                .ok()
                .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.OPTIONS)
                .build();
    }

    @RequestMapping(value = "/authors/{id}", method = RequestMethod.OPTIONS)
    ResponseEntity<?> singularOptions3(){
        return ResponseEntity
                .ok()
                .allow(HttpMethod.GET,HttpMethod.DELETE, HttpMethod.PUT, HttpMethod.OPTIONS)
                .build();
    }



    @GetMapping
    public String greetings(){
        return "Hello. This is the bookstore.";
    }
}
