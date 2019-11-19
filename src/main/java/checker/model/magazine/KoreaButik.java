package checker.model.magazine;

import checker.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Alexander Diachenko
 */
public class KoreaButik extends AbstractMagazine {

    private static final String DATA_QAID = "data-qaid";
    private static final String PRODUCT_PRICE = "product_price";
    private static final String SITE_DOMAIN = "korea-butik.com";
    private static final String PRESENCE_DATA = "presence_data";
    private static final String OUT_OF_STOCK = "Нет в наличии";

    @Override
    protected String getPriceFrom(Document document) {
        Elements prices = document.getElementsByAttributeValue(DATA_QAID, PRODUCT_PRICE);
        return prices.stream()
                .findFirst()
                .map(Element::text)
                .map(StringUtil::formatPrice)
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    protected String getSiteDomain() {
        return SITE_DOMAIN;
    }

    @Override
    public boolean isAvailable(Document document) {
        Elements presenceData = document.getElementsByAttributeValue(DATA_QAID, PRESENCE_DATA);
        return presenceData.stream()
                .map(Element::text)
                .anyMatch(price -> !OUT_OF_STOCK.equalsIgnoreCase(price));
    }
}
