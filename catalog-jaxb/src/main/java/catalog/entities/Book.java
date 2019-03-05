package catalog.entities;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"year", "title"})
public class Book {

    private String title;

    private String isbn10;

    private long year;

    public Book() {
    }

    public Book(String title, String isbn10, long year) {
        this.title = title;
        this.isbn10 = isbn10;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlAttribute
    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    @XmlElement(name = "release-year")
    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }
}
