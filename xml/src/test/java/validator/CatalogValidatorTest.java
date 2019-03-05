package validator;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CatalogValidatorTest {

    @Test
    public void testValidate() {
        boolean valid = new CatalogValidator().validateByXsd(CatalogValidatorTest.class
        .getResourceAsStream("/catalog-validate.xml"));

        assertTrue(valid);
    }
}
