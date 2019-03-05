package sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BookSaxHandler extends DefaultHandler {

    private Deque<String> names = new ArrayDeque<>();

    private List<Book> books = new ArrayList<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("uri: " + uri);
        System.out.println("localName: " + localName);
        System.out.println("qName: " + qName);
        names.push(localName);
        if ("book".equals(localName)) {
            Book book = new Book("", attributes.getValue("isbn10"));
            books.add(book);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        names.pop();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String name = names.peek();
        if ("title".equals(name)) {
            Book book = books.get(books.size() - 1);
            book.setTitle(book.getTitle() + new String(ch, start, length));
        }
    }

    public List<Book> getBooks() {
        return books;
    }
}
