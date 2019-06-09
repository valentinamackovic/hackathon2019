package rs.novisad.crimetime.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import rs.novisad.crimetime.entity.*;
import rs.novisad.crimetime.service.ArticleServiceInterface;

import java.io.IOException;
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

    public void getArticles(ArticleServiceInterface articleService) {
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
                            getArticleContext(tempArticle);
                            articleService.save(tempArticle);
                            System.out.println("Loading article number " + ++numberOfAricles + "...");
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    public void getArticleContext(Aricle article) {
        String content = article.getContent();
        content = content.replace(".", " ").replace("!", " ").replace(",", " ").replace("?", " ");

        for (String w : KeyWords.words) {
            if (content.toLowerCase().contains(w.toLowerCase()) && (w.equals("ubist") || w.equals("upuc")
                    || w.equals("ranjen") || w.equals("pucnjav") || w.equals("silov"))) {
                article.setCrimeCategory(CrimeCategory.TezaKrivicnaDela);
            } else if (content.toLowerCase().contains(w.toLowerCase()) && (w.equals("pljacka") || w.equals("opljacka")
                    || w.equals("pretuc") || w.equals("utuc") || w.equals("povredj") || w.equals("napad")
                    || w.equals("nesta") || w.equals("dilova") || w.equals("pretuk"))) {
                article.setCrimeCategory(CrimeCategory.LaksaKrivicnaDela);
            } else {
                article.setCrimeCategory(CrimeCategory.Prekrsaj);
            }
        }
    }
}