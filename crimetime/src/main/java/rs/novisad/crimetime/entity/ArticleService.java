package rs.novisad.crimetime.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService implements ArticleServiceInterface{

	@Autowired
	ArticleRepository articleRepository;
	
	@Override
	public Aricle findOne(Integer id){
		return articleRepository.findById(id).orElse(null);
	}
	
	@Override
	public List<Aricle> findAll() {
		return articleRepository.findAll();
	}

	@Override
	public Aricle save(Aricle a) {
		return articleRepository.save(a);
	}

	@Override
	public void remove(Integer id) {
//		articleRepository.delete(id);
	}

	
}
