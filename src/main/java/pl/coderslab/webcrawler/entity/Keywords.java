package pl.coderslab.webcrawler.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "keywords")
public class Keywords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keyword;

    @ManyToMany
    @JoinTable(name = "emails_keywords",
            joinColumns = @JoinColumn(name = "id_keyword"),
            inverseJoinColumns = @JoinColumn(name = "id_email"))
    private List<Email> emails = new ArrayList<>();

    public Keywords() {
    }

    public Keywords(String keyword, List<Email> emails) {
        this.keyword = keyword;
        this.emails = emails;
    }

    public Keywords(String keyword) {
        this.keyword = keyword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public String printEmails() {
        return emails.stream().map(em -> em.toString()).collect(Collectors.joining(" "));
    }
}
