package transform;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;
import java.io.StringWriter;

public class HtmlTransformer {

    public String transform(InputStream inputStream) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer(
                    new StreamSource(HtmlTransformer.class.getResourceAsStream("/catalog.xslt")));
            StringWriter result = new StringWriter();
            transformer.transform(
                    new StreamSource(inputStream), new StreamResult(result)
            );
            return result.toString();
        } catch (TransformerException e) {
            throw new RuntimeException("Error transforming xml", e);
        }

    }
}
