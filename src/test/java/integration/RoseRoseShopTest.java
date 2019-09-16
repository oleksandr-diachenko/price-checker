package integration;

import checker.model.magazine.Magazine;
import checker.model.magazine.RoseRoseShop;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author Alexander Diachenko.
 */
public class RoseRoseShopTest {

    private Magazine roseRoseShop;

    @Before
    public void setUp() {
        roseRoseShop = new RoseRoseShop();
    }

    @Test
    public void shouldReturnPageNotFound() {
        String price = roseRoseShop.getPrice(roseRoseShop.getDocument("https://www.roseroseshop.com/qwe"));
        assertEquals("Страница не найдена", price);
    }

    @Test
    public void shouldReturnNotEmptyDocument() throws IOException {
        Document document = roseRoseShop.getDocument("https://www.roseroseshop.com/");
        assertFalse(document.children().isEmpty());
    }
}
