package pos.proiect.bookstore.service.interfaces;

import pos.proiect.bookstore.model.Order;

import java.util.List;
import java.util.Map;

public interface OrderServiceInterface {
    List<Order> getAllOrdersByUser(Integer user_id);

    Order createNewOrder(Map<String, String> params );

    void placeOrder(Integer user_id, Order order);
    void deleteOrder(String id);
    //void updateOrder(Order order);

}
