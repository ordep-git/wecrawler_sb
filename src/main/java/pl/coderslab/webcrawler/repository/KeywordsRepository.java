package pl.coderslab.webcrawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.webcrawler.entity.Keywords;

@Repository
public interface KeywordsRepository extends JpaRepository<Keywords, Long> {
}
