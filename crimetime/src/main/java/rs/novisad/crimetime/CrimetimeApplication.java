package rs.novisad.crimetime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import rs.novisad.crimetime.crawler.Extractor;
import rs.novisad.crimetime.entity.Cluster;
import rs.novisad.crimetime.global.var;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
public class CrimetimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrimetimeApplication.class, args);
	}

}
