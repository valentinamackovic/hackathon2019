package rs.novisad.crimetime;

import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rs.novisad.crimetime.crawler.Extractor;
import rs.novisad.crimetime.crawler.JSONArticleParser;
import rs.novisad.crimetime.entity.Aricle;
import rs.novisad.crimetime.entity.Cluster;
import rs.novisad.crimetime.entity.*;
import rs.novisad.crimetime.global.var;
import rs.novisad.crimetime.helper.AddressProcessor;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
public class CrimetimeApplication {
	
	public static ArrayList<Aricle> articles=new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(CrimetimeApplication.class, args);
		
		JSONArticleParser parser=new JSONArticleParser();
		parser.populateFilesList();
		parser.JsonToArticle();
	}

}
