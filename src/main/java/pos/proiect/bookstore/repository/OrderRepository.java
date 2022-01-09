package pos.proiect.bookstore.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pos.proiect.bookstore.model.Order;

public interface OrderRepository extends MongoRepository<Order, String>, OrderRepositoryCustom {

    Order findOrderByUserid(Integer user_id);


}
