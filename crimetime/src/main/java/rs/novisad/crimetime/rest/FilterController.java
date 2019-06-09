package rs.novisad.crimetime.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.novisad.crimetime.crawler.Extractor;
import rs.novisad.crimetime.entity.Aricle;
import rs.novisad.crimetime.service.ArticleServiceInterface;
import rs.novisad.crimetime.entity.Cluster;
import rs.novisad.crimetime.global.var;
import rs.novisad.crimetime.helper.AddressProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Map<String, Object> retVal = new HashMap<>();
		int total = 0;

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
		for (Cluster cluster : var.clusters
			 ) {
			if (cluster.getNumberOfAccidents() > total)
				total = cluster.getNumberOfAccidents();
		}
    	retVal.put("green", total/4);
		retVal.put("yellow", total/2);
		retVal.put("orange", total*3/4);
		retVal.put("red", total);
    	retVal.put("clusters", var.clusters);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }
}
