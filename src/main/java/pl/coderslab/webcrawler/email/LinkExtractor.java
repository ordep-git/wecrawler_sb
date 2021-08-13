package pl.coderslab.webcrawler.email;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LinkExtractor {

    private static final String GOOGLE_PRE = "https://www.google.com/search?start=";
    List<String> links = new ArrayList<>();

    public List<String> searchLinks(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> links = new ArrayList<>();
        Elements elements = document.select("a[href]");
        for (Element e : elements) {
            links.add(e.attr("href"));
        }
        return links;
    }

    public List<String> pagesGoogle(int numberOfPages, String keywordsList) {
        String urlGoogle;
        int start = 0;
        for (int i = 0; i < numberOfPages; i++) {
            urlGoogle = GOOGLE_PRE + start + "&q=email+" + keywordsList;
            links.addAll(searchLinks(urlGoogle));
            start += 10;
        }
        return links;
    }
}
