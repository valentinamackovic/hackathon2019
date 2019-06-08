package rs.novisad.crimetime.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import rs.novisad.crimetime.entity.Aricle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Extractor {
    private HashSet<String> links;
    private List<Aricle> articles;

    private final String DATA_CRAWLER_PATH = System.getProperty("user.dir") + "\\crimetime\\crawler_data\\";

    public Extractor() {
        links = new HashSet<>();
        articles = new ArrayList<>();
    }

    public void getPageLinks(String site_path) {
        if (!links.contains(site_path)) {
            try {
                Document document = Jsoup.connect(site_path).get();
                Elements otherLinks = document.getElementsByClass("news");
                for (Element element : otherLinks) {
                    String rel_path = element.select("a").attr("href");
                    links.add(site_path + rel_path);
                    getPageLinks(site_path + rel_path);
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void getArticles() {
        links.forEach(x -> {
            Document document;
            try {
                for (String link : links) {
                    document = Jsoup.connect(link).get();
                    Element article = document.select("h1").first();
                    Element single = document.getElementsByClass("single").first();
                    if (article.text().matches("^.*?(pretu|Fakultet|FAKULTET).*$")) {
                        Aricle tempArticle = new Aricle(article.text(), link, single.text());
                        if (articles.stream().filter(a -> a.getTitle().equalsIgnoreCase(tempArticle.getTitle())).findFirst().orElse(null) == null)
                            articles.add(tempArticle);
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    public void writeToFile(String filename) {
        FileWriter writer;
        final String FILE_PATH = DATA_CRAWLER_PATH + filename;
        try {
            new File(FILE_PATH);
            writer = new FileWriter(FILE_PATH);
            articles.forEach(a -> {
                try {
                    String temp = "- Title: " + a.getTitle() + " (link: " + a.getUrl() + ")\n";
                    System.out.println(temp);
                    writer.write(temp);
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            });
            writer.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}