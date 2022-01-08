package pos.proiect.bookstore.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pos.proiect.bookstore.model.Order;
import pos.proiect.bookstore.repository.OrderRepository;
import pos.proiect.bookstore.service.interfaces.OrderServiceInterface;

import java.util.List;

@Service
public class OrderService implements OrderServiceInterface {


    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

   @Override
    public Order findOrderByUser(Integer user_id) {
       return orderRepository.findOrderByUserid(user_id);

   }

    @Override
    public Order saveOrUpdateOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }
}
