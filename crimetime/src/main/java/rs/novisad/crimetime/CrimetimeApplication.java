package rs.novisad.crimetime;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import rs.novisad.crimetime.entity.Aricle;
import rs.novisad.crimetime.global.var;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;




@SpringBootApplication
public class CrimetimeApplication {

	public static ArrayList<Aricle> articles=new ArrayList<>();
	
	public static void main(String[] args) {
		var.clusters.size();
		SpringApplication.run(CrimetimeApplication.class, args);
		
	}
	
	

}
