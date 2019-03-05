package transform;

import org.junit.Test;

public class HtmlTransformerTest {

    @Test
    public void testTransform() {
        String html = new HtmlTransformer().transform(HtmlTransformerTest.class
        .getResourceAsStream("/catalog-validate.xml"));

        System.out.println(html);
    }
}
