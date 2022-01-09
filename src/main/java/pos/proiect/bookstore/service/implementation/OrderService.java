package pos.proiect.bookstore.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pos.proiect.bookstore.model.Order;
import pos.proiect.bookstore.repository.OrderRepository;
import pos.proiect.bookstore.service.interfaces.OrderServiceInterface;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrderService implements OrderServiceInterface {


    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

   @Override
   public List<Order> getAllOrdersByUser(Integer user_id) {
        orderRepository.setCollectionName("client."+user_id.toString());
        return orderRepository.findAll();

   }

    @Override
    public Order createNewOrder(Map<String, String> params) {
        return new Order(LocalDateTime.now(), new ArrayList<>(), "initialized");
    }

    @Override
    public void placeOrder(Integer user_id, Order order) {
        orderRepository.setCollectionName("client."+user_id.toString());
        orderRepository.insert(order);
    }

    @Override
    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }
}
