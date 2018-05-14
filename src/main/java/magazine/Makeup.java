package magazine;

import org.apache.log4j.Logger;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriverException;
import url.UrlUtils;

import java.net.MalformedURLException;
import java.util.Properties;

/**
 * @author Alexander Diachenko.
 */
public class Makeup implements Magazine {

    private final static Logger logger = Logger.getLogger(Makeup.class);

    private Properties properties;
    private String id;

    public Makeup(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String getPrice(String url) {
        Document document = getDocument(url);
        if (document == null) {
            return "Страница не найдена";
        }
        return getValue(document);
    }

    @Override
    public boolean isCorrectPage(Document document) {
        return !document.select("h1.page-header").text().equals("Страница не найдена");
    }

    @Override
    public boolean isThisWebsite(String url) {
        try {
            return UrlUtils.getDomainName(url).equals("makeup.com.ua");
        } catch (MalformedURLException e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public Document getDocument(String url) throws WebDriverException {
        String[] split = url.split("/");
        id = split[split.length - 1];
        try {
            return Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                    .get();
        } catch (HttpStatusException ex) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    private String getValue(Document document) {
        if (!isAvailable(document)) {
            return "Нет в наличии";
        } else {
            final Elements elementsByAttributeValue = document.getElementsByAttributeValue("data-variant-id", id);
            if (elementsByAttributeValue.size() == 0) {
                return document.select("span.product-item__price > span.rus").text();
            }
            for (Element element : elementsByAttributeValue) {
                return element.attr("data-price");
            }
            return "Не найдено";
        }
    }

    @Override
    public boolean isDiscount(Document document) {
        final Element status = document.getElementById("product_enabled");
        return status.text().equalsIgnoreCase("Есть в наличии!");
    }

    @Override
    public boolean isAvailable(Document document) {
        final Element status = document.getElementById("product_enabled");
        return status.text().equalsIgnoreCase("Есть в наличии!");
    }
}
