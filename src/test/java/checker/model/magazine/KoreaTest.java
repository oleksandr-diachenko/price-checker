package checker.model.magazine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Alexander Diachenko
 */
public class KoreaTest {

    private Magazine korea;
    private DocumentCreator creator;

    @Before
    public void setup() {
        korea = new Korea();
        creator = new DocumentCreator();
    }

    @Test
    public void shouldReturnDiscountPrice() {
        String price = korea.getPrice(creator.createDocumentFromFile("xml/korea/Korea_discount.xml"));
        assertEquals("380.00", price);
    }

    @Test
    public void shouldReturnNormalPrice() {
        String price = korea.getPrice(creator.createDocumentFromFile("xml/korea/Korea_normal.xml"));
        assertEquals("430.00", price);
    }

    @Test
    public void shouldReturnOutOfStock() {
        String price = korea.getPrice(creator.createDocumentFromFile("xml/korea/Korea_outofstock.xml"));
        assertEquals("Нет в наличии", price);
    }
}
