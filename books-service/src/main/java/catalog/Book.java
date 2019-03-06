package catalog;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Book {

    private String title;

    private String isbn10;

    public Book() {
    }

    public Book(String title, String isbn10) {
        this.title = title;
        this.isbn10 = isbn10;
    }


    public String getTitle() {
        return title;
    }

    @XmlElement(required = true)
    public void setTitle(String title) {
        this.title = title;
    }

    @XmlAttribute(required = true)
    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }
}
