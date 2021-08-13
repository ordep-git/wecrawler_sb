package pl.coderslab.webcrawler.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;

@Entity
@Table(name = "emails")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String email;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email)) return false;
        Email email1 = (Email) o;
        return Objects.equals(id, email1.id) && Objects.equals(email, email1.email) && Objects.equals(urls, email1.urls) && Objects.equals(keywords, email1.keywords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, urls, keywords);
    }
}
