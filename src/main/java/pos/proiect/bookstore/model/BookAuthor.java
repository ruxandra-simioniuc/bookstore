package pos.proiect.bookstore.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="carte_autor")
public class BookAuthor {

    @EmbeddedId
    BookAuthorPK bookAuthorPK;

    @Column(name="nr_ordine")
    Integer order_no;

    public BookAuthorPK getBookAuthorPK() {
        return bookAuthorPK;
    }

    public void setBookAuthorPK(BookAuthorPK bookAuthorPK) {
        this.bookAuthorPK = bookAuthorPK;
    }

    public Integer getOrder_no() {
        return order_no;
    }

    public void setOrder_no(Integer order_no) {
        this.order_no = order_no;
    }
}
