package pl.coderslab.webcrawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.coderslab.webcrawler.entity.Email;
import pl.coderslab.webcrawler.entity.Keywords;

import java.util.List;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

//    @Query("select distinct e from Email e")
//    List<Email> findDistinctByEmail(List<Email> email);

    @Query(value = "SELECT DISTINCT emails.id, emails.email, urls.url FROM emails, emails_urls, urls WHERE emails.id = emails_urls.id_email AND emails_urls.id_url = urls.id", nativeQuery = true)
    List<Email> findAllByEmails();

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Email e WHERE e.email = :email")
    boolean existsByEmail(@Param("email") String email);

    @Query("select distinct e from Email e")
    List<Email> findDistinctByEmails();
}
