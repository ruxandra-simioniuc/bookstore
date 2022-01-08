package pos.proiect.bookstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;

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
    private Integer publishing_year;

    @Column(name = "gen_literar")
    private String genre;

    @Column(name="pret")
    private Float price;

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

    public Integer getPublishing_year() {
        return publishing_year;
    }

    public void setPublishing_year(Integer publishing_year) {
        this.publishing_year = publishing_year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Float getPret() {
        return price;
    }

    public void setPret(Float price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}