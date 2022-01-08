package pos.proiect.bookstore.model;

public class BookInfoOrder implements BookInterface{

    private String isbn;
    private String title;
    private Double price;
    private Integer stock;

    public BookInfoOrder(String isbn, String title, Double price, Integer stock) {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.stock = stock;
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
        return stock;
    }

    public void setQuantity(Integer stock) {
        this.stock = stock;
    }
}
