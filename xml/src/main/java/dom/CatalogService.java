package dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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

public class CatalogService {

    public List<Book> parse(InputStream inputStream) {
        try {
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);
            NodeList books = document.getElementsByTagName("book");
            List<Book> bookList = new ArrayList<>();
            for (int i = 0; i < books.getLength(); i++) {
                Element book = (Element) books.item(i);
                String isbn10 = book.getAttribute("isbn10");
                Element title = (Element) book.getElementsByTagName("title")
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
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element catalogElement = document.createElement("catalog");
            document.appendChild(catalogElement);

            for (Book book: books) {
                Element bookElement = document.createElement("book");
                catalogElement.appendChild(bookElement);
                bookElement.setAttribute("isbn10", book.getIsbn10());

                Element titleElement = document.createElement("title");
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
