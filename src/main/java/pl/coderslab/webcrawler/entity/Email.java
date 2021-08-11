package pl.coderslab.webcrawler.entity;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;

@Entity
@Table(name = "emails")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull
    private String email;

//    @NotEmpty
    @ManyToMany(mappedBy = "emails", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Url> urls = new ArrayList<>();

    @ManyToMany(mappedBy = "emails", cascade = CascadeType.REMOVE)
    private List<Keywords> keywords = new ArrayList<>();

    public Email(Email mail, List<Url> link) {
    }

    public Email(String email, String url) {
        this.email = email;
        this.urls = Arrays.asList(new Url(url));
    }


    public void addUrl(Url url) {
        urls.add(url);
    }

    public void addKeyword(Keywords keyword) {
        keywords.add(keyword);
    }

    public Email() {
    }
    public Email(String email) {
        this.email = email;
    }

    public Email(String email, List<Url> urls, List<Keywords> keywords) {
        this.email = email;
        this.urls = urls;
        this.keywords = keywords;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Url> getUrls() {
        return urls;
    }

    public List<Keywords> getKeywords() {
        return keywords;
    }

    @Override
    public String toString() {
        return email;
    }

    public String printUrls() {
        return urls.stream().map(url -> url.toString()).collect(Collectors.joining(" "));
    }
}
