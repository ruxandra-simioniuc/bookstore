package pos.proiect.bookstore.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pos.proiect.bookstore.dto.Item;
import pos.proiect.bookstore.model.Order;
import pos.proiect.bookstore.repository.OrderRepository;
import pos.proiect.bookstore.service.interfaces.OrderServiceInterface;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OrderService implements OrderServiceInterface {


    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

   @Override
   public List<Order> getAllOrdersByUser(Integer user_id) {
        this.setUserId(user_id);
        return orderRepository.findAll();

   }

    @Override
    public Order createNewOrder(Map<String, String> params) {
        return new Order(LocalDateTime.now(), new ArrayList<>(), "initialized");
    }

    @Override
    public void placeOrder(Order order) {
        orderRepository.insert(order);
    }

    @Override
    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void addItemsToOrder( List<Item> items) {

        List<Order> existingOrders = orderRepository.findOrderByStatus("initialized");

        Order temp_order = existingOrders.get(0);
        List<Item> temp = temp_order.getItems();
        List<Item> concat = Stream.concat(temp.stream(), items.stream()).collect(Collectors.toList());
        temp_order.setItems(concat);
        orderRepository.save(temp_order);

    }

    @Override
    public boolean orderExistsForUser() {
        List<Order> existingOrders = orderRepository.findOrderByStatus("initialized");
        System.out.println("lista: " + existingOrders.size());

        return !existingOrders.isEmpty();
    }

    @Override
    public void setUserId(Integer user_id) {
        orderRepository.setCollectionName("client."+user_id.toString());
    }


}
