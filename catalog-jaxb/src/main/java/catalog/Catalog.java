package catalog;

import java.util.ArrayList;
import java.util.List;

public class Catalog {

    private List<Book> books = new ArrayList<>();

    public Catalog() {
    }

    public Catalog(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
