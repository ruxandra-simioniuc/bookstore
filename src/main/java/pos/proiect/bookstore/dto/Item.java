package pos.proiect.bookstore.dto;

public class Item {
    private String isbn;
    private String title;
    private Double price;
    private Integer quantity;

    public Item(String isbn, String title, Double price, Integer quantity) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
