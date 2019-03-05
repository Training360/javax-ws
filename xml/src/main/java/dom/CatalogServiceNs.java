package dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class CatalogServiceNs {

    public static final String CATALOG_NS = "http://training360.com/schemas/catalog";

    public List<Book> parse(InputStream inputStream) {
        try {
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();

            factory.setNamespaceAware(true);

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);
//            NodeList books = document.getElementsByTagName("c:book");
            NodeList books = document.getElementsByTagNameNS(CATALOG_NS, "book");
            List<Book> bookList = new ArrayList<>();
            for (int i = 0; i < books.getLength(); i++) {
                Element book = (Element) books.item(i);

                System.out.println(book.getNamespaceURI());
                System.out.println(book.getLocalName());
                System.out.println(book.getPrefix());

                String isbn10 = book.getAttribute("isbn10");
//                Element title = (Element) book.getElementsByTagName("c:title")
//                        .item(0);
                Element title = (Element) book
                        .getElementsByTagNameNS(CATALOG_NS, "title")
                        .item(0);
                String titleValue = title.getTextContent();
                bookList.add(new Book(titleValue, isbn10));
            }
            return bookList;
        }
        catch (Exception e) {
            throw new IllegalStateException("Can not parse xml", e);
        }
    }

    public String writeToXml(List<Book> books) {
        try {
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();

            factory.setNamespaceAware(true);

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element catalogElement = document.createElementNS(CATALOG_NS, "catalog");
            catalogElement.setPrefix("c");
//            catalogElement.setAttribute("xmlns:c", CATALOG_NS);

            document.appendChild(catalogElement);

            for (Book book: books) {
                Element bookElement = document.createElementNS(CATALOG_NS,"book");
                bookElement.setPrefix("c");
                catalogElement.appendChild(bookElement);
                bookElement.setAttribute("isbn10", book.getIsbn10());

                Element titleElement = document.createElementNS(CATALOG_NS, "title");
                titleElement.setPrefix("c");
                bookElement.appendChild(titleElement);

                titleElement.setTextContent(book.getTitle());
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(document);

            StringWriter stringWriter = new StringWriter();
            StreamResult result = new StreamResult(stringWriter);

            transformer.transform(source, result);
            return stringWriter.toString();
        }
        catch (Exception e) {
            throw new IllegalStateException("Can not write xml", e);
        }
    }
}
