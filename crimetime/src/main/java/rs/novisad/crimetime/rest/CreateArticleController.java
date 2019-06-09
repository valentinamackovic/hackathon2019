package rs.novisad.crimetime.rest;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rs.novisad.crimetime.CrimetimeApplication;
import rs.novisad.crimetime.entity.Aricle;
import rs.novisad.crimetime.entity.CrimeCategory;
import rs.novisad.crimetime.service.ArticleServiceInterface;

@Controller
@RequestMapping("/articles")
public class CreateArticleController {

	@Autowired
	private ArticleServiceInterface articleService;
	
	@GetMapping("/saveArticles")
	public ResponseEntity<Object> saveAllArticles() {
		
		System.out.println(CrimetimeApplication.articles.size());
		for(Aricle a: CrimetimeApplication.articles) {
				System.out.println(a.getTitle());
				articleService.save(a);
		}
		return new ResponseEntity<>("Successful", HttpStatus.OK);
	}
	
	@GetMapping("/getPrekrsaji")
	public ResponseEntity<Object> getArticles() {
		ArrayList<Aricle> articles=new ArrayList<>();
		
		CrimetimeApplication.articles.addAll(articleService.findAll());
		System.out.println(CrimetimeApplication.articles.size());
		for(Aricle a: CrimetimeApplication.articles) {
			System.out.println(a.getId());
			if(a.getCrimeCategory()==CrimeCategory.Prekrsaj)
				articles.add(a);
		}
		return new ResponseEntity<>("Successful", HttpStatus.OK);
//		return articles;
	}
	
	@GetMapping("/getLaksaKrivicnaDela")
	public ArrayList<Aricle> getArticleslaksaKrivicnaDela() {
		ArrayList<Aricle> articles=new ArrayList<>();
		CrimetimeApplication.articles.addAll(articleService.findAll());
		for(Aricle a: CrimetimeApplication.articles) {
			if(a.getCrimeCategory()==CrimeCategory.LaksaKrivicnaDela)
				articles.add(a);
		}
		return articles;
	}

}
