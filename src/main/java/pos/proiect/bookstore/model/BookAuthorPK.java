package pos.proiect.bookstore.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BookAuthorPK implements Serializable {

    @Column(name="isbn")
    String isbn;

    @Column(name="id_autor")
    String id_author;

    public BookAuthorPK() {

    };

    public BookAuthorPK(String isbn, String id_author) {
        this.isbn = isbn;
        this.id_author = id_author;
    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getId_author() {
        return id_author;
    }

    public void setId_author(String id_author) {
        this.id_author = id_author;
    }
}
