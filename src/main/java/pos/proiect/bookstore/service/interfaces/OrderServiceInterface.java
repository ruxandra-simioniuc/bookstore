package pos.proiect.bookstore.service.interfaces;

import pos.proiect.bookstore.model.Order;

import java.util.List;

public interface OrderServiceInterface {
    List<Order> getAllOrders();
    Order findOrderByUser(Integer user_id);

    Order saveOrUpdateOrder(Order order);
    void deleteOrder(String id);

}
