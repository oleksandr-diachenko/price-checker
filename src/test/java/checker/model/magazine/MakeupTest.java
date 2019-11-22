package checker.model.magazine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Alexander Diachenko
 */
class MakeupTest {

    private Makeup makeup;
    private DocumentCreator creator;

    @BeforeEach
    void setup() {
        makeup = new Makeup();
        creator = new DocumentCreator();
    }

    @Test
    void shouldReturnDiscountPrice() {
        makeup.url = "https://makeup.com.ua/product/632463/#/option/1558727/";
        String price = makeup.getPrice(creator.createDocumentFromFile("xml/makeup/Makeup_discount.xml"));
        assertEquals("209", price);
    }

    @Test
    void shouldReturnNormalPrice() {
        makeup.url = "https://makeup.com.ua/product/1801/#/option/393587/";
        String price = makeup.getPrice(creator.createDocumentFromFile("xml/makeup/Makeup_normal.xml"));
        assertEquals("218", price);
    }

    @Test
    void shouldReturnOutOfStock() {
        makeup.url = "https://makeup.com.ua/product/20652/";
        String price = makeup.getPrice(creator.createDocumentFromFile("xml/makeup/Makeup_outofstock.xml"));
        assertEquals("Нет в наличии", price);
    }

    @Test
    void shouldReturnNotFound() {
        makeup.url = "https://makeup.com.ua/product/20652/";
        String price = makeup.getPrice(creator.createDocumentFromFile("xml/makeup/Makeup_notfound.xml"));
        assertEquals("Не найдено", price);
    }

    @Test
    void shouldReturnTrueWhenIsThisWebSiteCalled() {
        assertTrue(makeup.isThisWebsite("https://makeup.com.ua/"));
    }

    @Test
    void shouldReturnFalseWhenIsThisWebSiteCalledWithGoogleDomain() {
        assertFalse(makeup.isThisWebsite("https://www.google.com.ua/"));
    }

    @Test
    void shouldReturnFalseWhenIsThisWebSiteCalledWithIncorrectDomain() {
        assertFalse(makeup.isThisWebsite("qwe"));
    }
}
