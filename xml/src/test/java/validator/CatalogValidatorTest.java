package validator;

import org.junit.Test;

public class CatalogValidatorTest {

    @Test
    public void testValidate() {
        new CatalogValidator().validateByXsd(CatalogValidatorTest.class
        .getResourceAsStream("/catalog-validate.xml"));
    }
}
