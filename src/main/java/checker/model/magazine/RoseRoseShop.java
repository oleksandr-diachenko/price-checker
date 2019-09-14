package checker.model.magazine;

import checker.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Alexander Diachenko
 */
public class RoseRoseShop extends AbstractMagazine {

    protected String getPriceFrom(Document document) {
        Elements price = document.getElementsByAttributeValue("itemprop", "price");
        if(price == null) {
            return null;
        }
        return StringUtil.formatPrice(price.text());
    }

    @Override
    protected String getSiteDomain() {
        return "www.roseroseshop.com";
    }

    @Override
    public boolean isAvailable(Document document) {
        Element cartButton = document.getElementById("button-cart");
        return cartButton != null;
    }
}
