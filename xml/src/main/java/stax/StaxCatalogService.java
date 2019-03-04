package stax;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class StaxCatalogService {


    public List<Book> parseWithCursor(InputStream inputStream) {
        try {
            XMLInputFactory f = XMLInputFactory.newInstance();
            XMLStreamReader r = f.createXMLStreamReader(inputStream);
            List<Book> books = new ArrayList<>();

            while (r.hasNext()) {
                if (r.getEventType() == XMLStreamConstants.START_ELEMENT) {
                    String tagName = r.getName().getLocalPart();
                    if ("book".equals(tagName)) {
                        books.add(new Book(null,
                                r.getAttributeValue(null, "isbn10")));
                    }
                    else if ("title".equals(tagName)) {
                        books.get(books.size() - 1).setTitle(r.getElementText());
                    }
                }
                r.next();
            }

            return books;
        }
        catch (XMLStreamException e) {
            throw new IllegalStateException("Can not parse xml", e);
        }
    }

    public List<Book> parseWithIterator(InputStream inputStream) {
        try {
            XMLInputFactory f = XMLInputFactory.newInstance();
            XMLEventReader r = f.createXMLEventReader(inputStream);
            List<Book> books = new ArrayList<>();

            while (r.hasNext()) {
                XMLEvent event = r.nextEvent();
                if (event instanceof StartElement) {
                    StartElement element = (StartElement) event;
                    if ("book".equals(element.getName().getLocalPart())) {
                        String isbn = element.getAttributeByName(new QName("isbn10")).getValue();
                        books.add(new Book(null, isbn));
                    } else if ("title".equals(element.getName().getLocalPart())) {
                        String title = r.getElementText();
                        Book book = books.get(books.size() - 1);
                        book.setTitle(title);
                    }
                }
            }

            return books;
        }
        catch (XMLStreamException e) {
            throw new IllegalStateException("Can not parse xml", e);
        }
    }

    public String writeToXml(List<Book> books) {
        try {
            XMLOutputFactory output = XMLOutputFactory.newInstance();
            StringWriter stringWriter = new StringWriter();
            XMLStreamWriter streamWriter = output.createXMLStreamWriter(stringWriter);
            streamWriter.writeStartDocument();
            streamWriter.writeStartElement("catalog");
            for (Book book: books) {
                streamWriter.writeStartElement("book");
                streamWriter.writeAttribute("isbn10", book.getIsbn10());
                streamWriter.writeStartElement("title");
                streamWriter.writeCharacters(book.getTitle());
                streamWriter.writeEndElement();
                streamWriter.writeEndElement();
            }
            streamWriter.writeEndElement();
            streamWriter.flush();
            return stringWriter.toString();
        } catch (XMLStreamException xse) {
            throw new IllegalStateException("Error writing xml", xse);
        }

    }
}
