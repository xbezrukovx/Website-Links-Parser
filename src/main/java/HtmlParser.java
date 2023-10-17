import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

public class HtmlParser {
    public static Vector<String> getLinks(String url) {
        Vector<String> links = new Vector<>();
        try {
            Thread.sleep(130);
            Connection connection = Jsoup.connect(url)
                    .ignoreHttpErrors(true);
            Document document = connection.get();
            Elements elements = document.body().getElementsByTag("a");

            for (Element element : elements) {
                String link = element.absUrl("href");
                if (isLink(link, url)) {
                    links.add(link);
                }
            }
        } catch (InterruptedException | IOException e) {
            System.out.println(e + " - " + url);
        }
        return links;
    }

    private static boolean isLink(String link, String owner) {
        try {
            URL url = new URL(link);
            String ownerHost = new URL(owner).getHost();
            String host = url.getHost();
            if (!host.equals(ownerHost) || link.endsWith(".jpg")
                    || link.endsWith(".png") || link.contains("#")
            ) {
                return false;
            }
        } catch (MalformedURLException e) {
            return false;
        }
        return true;
    }
}
