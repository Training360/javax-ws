package validator;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.InputStream;

public class CatalogValidator {

    public boolean validateByXsd(InputStream inputStream) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(
                    new StreamSource(CatalogValidator.class.getResourceAsStream("/catalog.xsd")));
            Validator validator = schema.newValidator();

//            validator.setErrorHandler();

            validator.validate(new StreamSource(inputStream));
            return true;
        }
        catch (SAXException | IOException e) {
            throw new IllegalStateException("Can not validate", e);
        }
    }
}
