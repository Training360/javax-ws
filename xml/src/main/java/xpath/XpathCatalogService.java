package xpath;

import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.*;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XpathCatalogService {

    public List<String> listTitles(InputStream inputStream) {
        try {
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            xpath.setNamespaceContext(new NamespaceContext() {
                @Override
                public String getNamespaceURI(String prefix) {
                    return "http://training360.com/schemas/catalog";
                }

                @Override
                public String getPrefix(String namespaceURI) {
                    return "c";
                }

                @Override
                public Iterator<String> getPrefixes(String namespaceURI) {
                    return List.of("c").iterator();
                }
            });
            XPathExpression expr = xpath.compile("/c:catalog/c:book/c:title/text()");
            NodeList value = (NodeList) expr.evaluate(
                    new InputSource(inputStream), XPathConstants.NODESET);

            List<String> values = new ArrayList<>();
            for (int i = 0; i < value.getLength(); i++) {
                values.add(value.item(i).getTextContent());
            }
            return values;
        }
        catch (XPathExpressionException e) {
            throw new IllegalStateException("Can not run xpath", e);
        }
    }

    public int getNumberOfBooks(InputStream inputStream) {
        return 0;
    }
}
