package pos.proiect.bookstore.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
import pos.proiect.bookstore.dto.Item;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection="#{@orderRepository.getCollectionName()}")
public class Order {
    @Id
    private String id;

    private Integer userid;
    //@DateTimeFormat
    //private Date date;
    private LocalDateTime date;

    @Field("items")
    private List<Item> items;
    private String status;

    public Order(){};

    public Order(LocalDateTime date, List<Item> items, String status) {
        this.date = date;
        this.items = items;
        this.status = status;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
