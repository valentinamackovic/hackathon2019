package rs.novisad.crimetime.crawler;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import rs.novisad.crimetime.entity.Aricle;
import rs.novisad.crimetime.entity.ConvertText;
import rs.novisad.crimetime.entity.KeyWords;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Extractor {

    private HashSet<String> links;
    private List<Aricle> articles;

    private String titleClassName;
    private String titleElementSingle;
    private String contentClassSingle;

//    private final String DATA_CRAWLER_PATH = System.getProperty("user.dir").replace("crimetime\\", "") + "\\crimetime\\crawler_data\\";
    private final String DATA_CRAWLER_PATH = System.getProperty("user.dir").replace("crimetime\\", "") + "\\crawler_data\\";

    public Extractor(String titleClassName, String titleElementSingle, String contentClassSingle) {
        links = new HashSet<>();
        articles = new ArrayList<>();
        this.titleClassName = titleClassName;
        this.titleElementSingle = titleElementSingle;
        this.contentClassSingle = contentClassSingle;
    }

    public void getPageLinks(String site_path) {
        if (!links.contains(site_path)) {
            try {
                Document document = Jsoup.connect(site_path).get();
                Elements otherLinks = document.getElementsByClass(titleClassName);
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
                    Element article = document.select(titleElementSingle).first();
                    Element single = document.getElementsByClass(contentClassSingle).first();
                    if (article.text().matches("^.*?(pretu|Fakultet|FAKULTET).*$")) {
                        Aricle tempArticle = new Aricle(article.text(), link, single.text());
                        String convertedContent=ConvertText.convert(tempArticle.getContent());
                        getArticleContext(convertedContent);
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
    
    public void getArticleContext(String content) {  	
    	
//    	String[] words=content.split(" ");
    	content=content.replace(".", " ").
    	replace("!", " ").
    	replace(",", " ").
    	replace("?", " ");
    	
    	for(String w : KeyWords.words) {		
//	        for (int i = 0; i < words.length; i++) {
	        	//kategorija ubistva
	           if (content.toLowerCase().contains(w.toLowerCase()) && (w.equals("ubist") || w.equals("upuc") || 
	        		   w.equals("ranjen")|| w.equals("pucnjav") || w.equals("silov"))) {
	              System.out.println("sadrzi tezi zlocin" );
	           }
	           else if(content.toLowerCase().contains(w.toLowerCase()) && (w.equals("pljacka") || w.equals("opljacka") || 
	        		   w.equals("pretuc")|| w.equals("utuc") || w.equals("povredj")|| w.equals("napad") || w.equals("nesta")
	        		   || w.equals("dilova")|| w.equals("pretuk") )) {
		              System.out.println("sadrzi laksi zlocin" );
	           }  
	           else if(content.toLowerCase().contains(w.toLowerCase()) && (w.equals("ukra") || w.equals("ukras") || 
	        		   w.equals("obij") )) {
		              System.out.println("sadrzi kradju" );
	           }
//	        }
    	}
    }
}