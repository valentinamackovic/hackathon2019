package rs.novisad.crimetime.crawler;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import rs.novisad.crimetime.CrimetimeApplication;
import rs.novisad.crimetime.entity.Aricle;
import rs.novisad.crimetime.entity.CrimeCategory;
import rs.novisad.crimetime.entity.KeyWords;

public class JSONArticleParser {
	static ArrayList<File> filesList=new ArrayList<>();
	static Gson gson=new Gson();
	
	static {
		populateFilesList();
		JsonToArticle();
	}

	public static void populateFilesList() {
		
		File file=new File(System.getProperty("user.dir").replace("crimetime\\", "") + "\\crawler_data\\");
//		File file=new File("C:\\Users\\HP\\hakaton2.0\\hackathon2019\\crimetime\\crawler_data");
		
		File[] files = file.listFiles();
		for(File f : files){
			if(f.isFile()) filesList.add(new File(f.getAbsolutePath()));
	    }
	}
	
	public static void JsonToArticle() {
		try {
			for(File file: filesList) {
				Reader reader= new FileReader(file);
				CrimetimeApplication.articles=gson.fromJson(reader, new TypeToken<List<Aricle>>(){}.getType());
				for(Aricle a: CrimetimeApplication.articles) {
					getArticleContext(a);
				}							
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
    
    public static void getArticleContext(Aricle article) {  	
    	String content=article.getContent();
    	content=content.replace(".", " ").
    	replace("!", " ").
    	replace(",", " ").
    	replace("?", " ");
    	
    	for(String w : KeyWords.words) {
	           if (content.toLowerCase().contains(w.toLowerCase()) && (w.equals("ubist") || w.equals("upuc") || 
	        		   w.equals("ranjen")|| w.equals("pucnjav") || w.equals("silov"))) {
	        	   article.setCrimeCategory(CrimeCategory.Ubistva);
	           }
	           else if(content.toLowerCase().contains(w.toLowerCase()) && (w.equals("pljacka") || w.equals("opljacka") || 
	        		   w.equals("pretuc")|| w.equals("utuc") || w.equals("povredj")|| w.equals("napad") || w.equals("nesta")
	        		   || w.equals("dilova")|| w.equals("pretuk") )) {
	        	   article.setCrimeCategory(CrimeCategory.FizickiNapadi);
	           }  
	           else if(content.toLowerCase().contains(w.toLowerCase()) && (w.equals("ukra") || w.equals("ukras") || 
	        		   w.equals("obij") )) {
	        	   article.setCrimeCategory(CrimeCategory.Pljacka);
	           }
    	}
    }
	
}
