package rs.novisad.crimetime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rs.novisad.crimetime.crawler.Extractor;

@SpringBootApplication
public class CrimetimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrimetimeApplication.class, args);
		Extractor bwc = new Extractor("news", "h1", "single");
		bwc.getPageLinks("https://novisad.com");
		bwc.getArticles();
		bwc.writeToFile("novisad.com.txt");
	}

}
