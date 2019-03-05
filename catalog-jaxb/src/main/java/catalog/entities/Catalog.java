package catalog.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "book-catalog")
public class Catalog {

    private List<Book> books = new ArrayList<>();

    public Catalog() {
    }

    public Catalog(List<Book> books) {
        this.books = books;
    }

    @XmlElementWrapper(name = "books")
    @XmlElement(name = "book")
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
