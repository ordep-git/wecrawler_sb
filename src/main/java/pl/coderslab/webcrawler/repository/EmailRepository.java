package pl.coderslab.webcrawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.webcrawler.entity.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
}
