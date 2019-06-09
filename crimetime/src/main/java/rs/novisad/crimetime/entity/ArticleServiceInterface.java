package rs.novisad.crimetime.entity;

import java.util.List;

public interface ArticleServiceInterface {

	Aricle findOne(Integer productId);
	
	List<Aricle> findAll();
	
	Aricle save(Aricle article);
	
	void remove(Integer id);

}
