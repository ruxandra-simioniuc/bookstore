package pos.proiect.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pos.proiect.bookstore.dto.Item;
import pos.proiect.bookstore.model.Order;
import pos.proiect.bookstore.service.implementation.BookService;
import pos.proiect.bookstore.service.implementation.OrderService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BookService bookService;

    /*@GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return new ResponseEntity<List<Order>>(orderService.getAllOrders(), HttpStatus.OK);
    }*/


    /*@GetMapping("/{user_id}")
    public ResponseEntity<List<Order>> getOrderByUserID(@PathVariable("user_id") Integer user_id){
        return new ResponseEntity<List<Order>>(orderService.getAllOrdersByUser(user_id), HttpStatus.OK);
    }*/

    @PostMapping("/{user_id}/placeOrder/{isbn}/{quantity}")
    public ResponseEntity<String> placeOrder(@PathVariable("user_id")Integer user_id, @PathVariable("isbn")String isbn, @PathVariable("quantity")Integer quantity){
        orderService.setUserId(user_id);

        String title = bookService.getBookByISBN(isbn).getTitle();
        Double price = bookService.getBookByISBN(isbn).getPrice();
        Item item = new Item(isbn, title, price, quantity);
        Order myOrder = new Order(LocalDateTime.now(), List.of(item), "initialized");

        // check stock and then decrease from sql db
        if(bookService.stockOk(isbn, quantity)){
            if(orderService.orderExistsForUser()) {
                orderService.addItemsToOrder(List.of(item));
                return new ResponseEntity<String>("Stock ok for " + title + ". Updated order.", HttpStatus.OK);
            }else {
                bookService.decreaseStock(isbn, quantity);
                orderService.placeOrder(myOrder);
                return new ResponseEntity<String>("Stock ok for " + title + ". Placed order.", HttpStatus.OK);
            }

        }
        return new ResponseEntity<String>("stock too low", HttpStatus.CONFLICT);
    }

}
