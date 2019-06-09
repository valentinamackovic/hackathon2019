package rs.novisad.crimetime.service;

import java.util.List;

import rs.novisad.crimetime.entity.Aricle;

public interface ArticleServiceInterface {

	Aricle findOne(Integer productId);
	
	List<Aricle> findAll();
	
	Aricle save(Aricle article);
	
	void remove(Integer id);

}
