package sax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.util.List;

public class SaxCatalogService {

    public List<Book> parse(InputStream is) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            BookSaxHandler handler = new BookSaxHandler();
            saxParser.parse(is, handler);
            return handler.getBooks();
        }
        catch (Exception e) {
            throw new IllegalStateException("Can not parse XML", e);
        }
    }
}
