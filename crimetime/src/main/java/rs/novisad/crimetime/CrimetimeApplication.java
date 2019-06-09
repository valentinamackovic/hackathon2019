package rs.novisad.crimetime;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import rs.novisad.crimetime.entity.Aricle;

@SpringBootApplication
public class CrimetimeApplication {

	public static ArrayList<Aricle> articles=new ArrayList<>();
	
	public static void main(String[] args) {
		SpringApplication.run(CrimetimeApplication.class, args);
		
	}

}
