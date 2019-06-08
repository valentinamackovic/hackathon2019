package rs.novisad.crimetime.crawler;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import rs.novisad.crimetime.entity.Aricle;
import rs.novisad.crimetime.entity.ConvertText;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Extractor {
    private static List<Aricle> articles;

    private final static String DATA_CRAWLER_PATH = System.getProperty("user.dir").replace("crimetime\\", "") + "\\crimetime\\crawler_data\\";
//    private final static String DATA_CRAWLER_PATH = System.getProperty("user.dir").replace("crimetime\\", "") + "\\crawler_data\\";

    public static int numberOfAricles = 0;

    private HashSet<String> links;

    private String contentClassName;
    private String titleClassName;
    private String titleElementSingle;
    private String contentClassSingle;
    private boolean includeSiteLink;
    private Document document;

    public Extractor(String contentClassName,
                     String titleClassName,
                     String titleElementSingle,
                     String contentClassSingle,
                     boolean includeSiteLink) {
        links = new HashSet<>();
        articles = new ArrayList<>();

        this.contentClassName = contentClassName;
        this.titleClassName = titleClassName;
        this.titleElementSingle = titleElementSingle;
        this.contentClassSingle = contentClassSingle;
        this.includeSiteLink = includeSiteLink;
    }

    public void getPageLinks(String site_path) {
        if (!links.contains(site_path)) {
            try {
                Document document = Jsoup.connect(site_path).get();
                Element content = document.getElementsByClass(contentClassName).first();
                Elements otherLinks = content.getElementsByClass(titleClassName);
                for (Element element : otherLinks) {
                    String rel_path = element.select("a").attr("href");
                    if (includeSiteLink) {
                        links.add(site_path.split(".com")[0] + ".com" + rel_path);
                        getPageLinks(site_path.split(".com")[0] + ".com" + rel_path);
                    } else {
                        links.add(rel_path);
                        getPageLinks(rel_path);
                    }
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void getArticles() {
        links.forEach(x -> {
            if (x.startsWith("http")) {
                try {
                    for (String link : links) {
                        if (!link.startsWith("http"))
                            continue;
                        document = Jsoup.connect(link).get();
                        Element article = document.select(titleElementSingle).first();
                        Element single = document.getElementsByClass(contentClassSingle).first();
                        Aricle tempArticle = new Aricle(
                                ConvertText.convert(article.text()),
                                link,
                                ConvertText.convert(single.text()));
                        if (articles.stream().filter(a -> a.getTitle().equalsIgnoreCase(tempArticle.getTitle())).findFirst().orElse(null) == null) {
                            articles.add(tempArticle);
                            System.out.println("Loading article number " + ++numberOfAricles + "...");
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    public static void writeToFile(String filename) {
        try {
            System.out.println("WRITING TO FILE...");
            final String FILE_PATH = DATA_CRAWLER_PATH + filename;
            File file = new File(FILE_PATH);
            if (!file.createNewFile())
                System.out.println("FAILED TO CREATE NEW FILE...");
            Gson gson = new Gson();
            Files.write(Paths.get(FILE_PATH), "[".getBytes(), StandardOpenOption.APPEND);
            for (Aricle article : articles
            ) {
                Files.write(Paths.get(FILE_PATH), (gson.toJson(article) + ",").getBytes(), StandardOpenOption.APPEND);
            }
            Files.write(Paths.get(FILE_PATH), "]".getBytes(), StandardOpenOption.APPEND);
            System.out.println("FILE READY!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}