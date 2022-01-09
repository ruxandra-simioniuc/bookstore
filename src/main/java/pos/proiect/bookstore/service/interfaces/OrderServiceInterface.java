package pos.proiect.bookstore.service.interfaces;

import pos.proiect.bookstore.dto.Item;
import pos.proiect.bookstore.model.Order;

import java.util.List;
import java.util.Map;

public interface OrderServiceInterface {
    List<Order> getAllOrdersByUser(Integer user_id);

    Order createNewOrder(Map<String, String> params );

    void placeOrder(Order order);
    void deleteOrder(String id);
    void addItemsToOrder( List<Item> items);
    boolean orderExistsForUser();
    void setUserId(Integer user_id);

}
