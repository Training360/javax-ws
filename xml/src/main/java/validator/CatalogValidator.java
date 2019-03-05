package validator;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

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


            MyErrorHandler myErrorHandler = new MyErrorHandler();
            validator.setErrorHandler(myErrorHandler);

            validator.validate(new StreamSource(inputStream));
            return myErrorHandler.isValid();
        }
        catch (SAXException | IOException e) {
            throw new IllegalStateException("Can not validate", e);
        }
    }

    private static class MyErrorHandler implements ErrorHandler {
        private boolean valid = true;

        @Override
        public void warning(SAXParseException exception) throws SAXException {

        }

        @Override
        public void error(SAXParseException exception) throws SAXException {
            valid = false;
            System.out.println(exception.getMessage());
        }

        @Override
        public void fatalError(SAXParseException exception) throws SAXException {

        }

        public boolean isValid() {
            return valid;
        }
    }
}
