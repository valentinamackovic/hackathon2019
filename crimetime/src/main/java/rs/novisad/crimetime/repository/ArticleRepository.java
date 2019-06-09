package rs.novisad.crimetime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.novisad.crimetime.entity.Aricle;

@Repository
public interface ArticleRepository extends JpaRepository<Aricle, Integer> {

}
