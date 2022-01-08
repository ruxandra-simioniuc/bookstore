package pos.proiect.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pos.proiect.bookstore.dto.OrderDTO;
import pos.proiect.bookstore.model.Order;
import pos.proiect.bookstore.service.implementation.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        return new ResponseEntity<List<Order>>(orderService.getAllOrders(), HttpStatus.OK);
    }


    @GetMapping("{user_id}")
    public ResponseEntity<Order> getOrderByUserID(@PathVariable("user_id") Integer user_id){
        return new ResponseEntity<Order>(orderService.findOrderByUser(user_id), HttpStatus.OK);
    }

   /* @PostMapping("/save")
    public ResponseEntity<?> saveOrUpdateOrder(@RequestBody OrderDTO orderDTO){
        orderService.saveOrUpdateOrder(Model.map(orderDTO, Order.class));
    }*/

}
