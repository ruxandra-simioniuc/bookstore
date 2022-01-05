package pos.proiect.bookstore.model;

public class BookVerboseFalse implements BookInterface{
    private String ISBN;
    private String title;
    private String genre;

    public BookVerboseFalse(String ISBN, String title, String genre) {
        this.ISBN = ISBN;
        this.title = title;
        this.genre = genre;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
