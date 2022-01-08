package pos.proiect.bookstore.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection="orders")
public class Order {
    @Id
    private String id;

    private Integer userid;
    @DateTimeFormat
    private Date date;

    @Field("items")
    private List<?> items;
    private String status;

    public Order(){};

    public Order(Integer user_id, Date date, List<Book> items, String status) {
        this.userid = user_id;
        this.date = date;
        this.items = new ArrayList<>(items);
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return userid;
    }

    public void setUser_id(Integer user_id) {
        this.userid = user_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<?> getItems() {
        return items;
    }

    public void setItems(List<?> items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
