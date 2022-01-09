package pos.proiect.bookstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="carte")
public class Book implements BookInterface{

    //@Id -> is primary key
    @Id
    private String isbn;

    @Column(name = "titlu")
    private String title;

    @Column(name = "editura")
    private String publisher;

    @Column(name = "an_publicare")
    private Integer year;

    @Column(name = "gen_literar")
    private String genre;

    @Column(name="pret")
    private Double price;

    @Column(name="stoc")
    private Integer stock;

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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer publishing_year) {
        this.year = publishing_year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}