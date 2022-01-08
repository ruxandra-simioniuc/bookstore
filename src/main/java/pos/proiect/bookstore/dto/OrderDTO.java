package pos.proiect.bookstore.dto;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class OrderDTO {
    private String id;
    private Integer userid;
    private Date date;
    private List<?> items;
    private String status;

    public OrderDTO(String id, Integer userid, Date date, List<?> items, String status) {
        this.id = id;
        this.userid = userid;
        this.date = date;
        this.items = items;
        this.status = status;
    }

    public OrderDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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
