package pos.proiect.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pos.proiect.bookstore.dto.Item;
import pos.proiect.bookstore.model.Order;
import pos.proiect.bookstore.service.implementation.OrderService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /*@GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return new ResponseEntity<List<Order>>(orderService.getAllOrders(), HttpStatus.OK);
    }*/


    /*@GetMapping("/{user_id}")
    public ResponseEntity<List<Order>> getOrderByUserID(@PathVariable("user_id") Integer user_id){
        return new ResponseEntity<List<Order>>(orderService.getAllOrdersByUser(user_id), HttpStatus.OK);
    }*/

    @PostMapping("/placeOrder/{user_id}")
    public void placeOrder(@PathVariable("user_id")Integer user_id){
        Item item = new Item("978-000-65-4606-1", "Fahrenheit 451", (float) 43.7, 1);
        Order myOrder = new Order(LocalDateTime.now(), List.of(item), "initialized");

        orderService.placeOrder(user_id, myOrder);

    }

}
