package pl.coderslab.webcrawler.repository;

import javassist.compiler.ast.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.webcrawler.entity.Email;
import pl.coderslab.webcrawler.entity.Keywords;
import java.util.List;

@Repository
public interface KeywordsRepository extends JpaRepository<Keywords, Long> {

    @Query("select distinct k from Keywords k")
    List<Keywords> findDistinctByKeywords();

    @Query(value = "SELECT  distinct emails.id, emails.email, keywords.keyword FROM emails, emails_keywords, keywords WHERE emails.id = emails_keywords.id_email AND emails_keywords.id_keyword = keywords.id", nativeQuery = true)
    List<Keywords> findlByEmails();
}
