package rs.novisad.crimetime.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.novisad.crimetime.crawler.Extractor;
import rs.novisad.crimetime.entity.ArticleServiceInterface;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/api")
public class LoadController {

    private ArticleServiceInterface articleService;

    public LoadController(ArticleServiceInterface articleService) {
        this.articleService = articleService;
    }

    private static final String DATA_PATH = "https://www.021.rs/Novi-Sad/Hronika/77/";
    private static final String FILE_NAME = "articles_all.json";
    private static final int NUMBER_OF_PAGES = 92;

    @GetMapping("/loadData")
    public ResponseEntity<Object> loadData() {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println("START DATE: " + df.format(new Date()));

        Extractor extractor = new Extractor("storyCatList","article_title", "h1", "story", false);
        extractor.getPageLinks(DATA_PATH);
        extractor.getArticles(articleService);

        for (int i = 21, j = 2; i <= NUMBER_OF_PAGES * 21; i+= 21, j++) {
            extractor = new Extractor("storyCatList","article_title", "h1", "story", false);
            extractor.getPageLinks(DATA_PATH + i);
            extractor.getArticles(articleService);
        }

        System.out.println("END DATE: " + df.format(new Date()));
        System.out.println("NUMBER OF ARTICLES: " + Extractor.numberOfAricles);
        System.out.println("END OF RESEARCH");

        return new ResponseEntity<>("Successful", HttpStatus.OK);
    }
}
