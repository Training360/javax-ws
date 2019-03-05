package stax;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class StaxCatalogServiceNs {

    public static final String CATALOG_NS = "http://training360.com/schemas/catalog";

    public String writeToXml(List<Book> books) {
        try {
            XMLOutputFactory output = XMLOutputFactory.newInstance();
            StringWriter stringWriter = new StringWriter();
            XMLStreamWriter streamWriter = output.createXMLStreamWriter(stringWriter);

            streamWriter.writeStartDocument();
            streamWriter.writeStartElement("c","catalog", CATALOG_NS);
            streamWriter.writeNamespace("c", CATALOG_NS);
            for (Book book: books) {
                streamWriter.writeStartElement("c", "book", CATALOG_NS);
                streamWriter.writeAttribute("isbn10", book.getIsbn10());
                streamWriter.writeStartElement("c", "title", CATALOG_NS);
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
