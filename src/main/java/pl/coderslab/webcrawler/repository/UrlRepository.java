package pl.coderslab.webcrawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.webcrawler.entity.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
}
