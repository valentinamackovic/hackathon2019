package rs.novisad.crimetime.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.novisad.crimetime.crawler.Extractor;
import rs.novisad.crimetime.entity.Aricle;
import rs.novisad.crimetime.entity.ArticleServiceInterface;
import rs.novisad.crimetime.entity.Cluster;
import rs.novisad.crimetime.global.var;
import rs.novisad.crimetime.helper.AddressProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api")
public class FilterController {
	
	private int br = 0;

    private ArticleServiceInterface articleService;

    public FilterController(ArticleServiceInterface articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/filter")
    public ResponseEntity<Object> doFilter() {

    	List<Aricle> allArticles = articleService.findAll();
    	
    	for(Aricle article : allArticles) {
    		String str = AddressProcessor.parseAdress(article.getContent());
    		if(!str.equals("")) {
    			for(Cluster cls : var.clusters) {
    				if(cls.getName().equals(str)) {
    					cls.setNumberOfAccidents(cls.getNumberOfAccidents() + 1);
    				}
    			}
    		}
    	}
    	
    	for(Cluster c : var.clusters) {
    		System.out.println(c.getName() + " - " + c.getNumberOfAccidents());
    	}

        return new ResponseEntity<>(articleService.findAll(), HttpStatus.OK);
    }
}
