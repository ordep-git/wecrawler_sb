package pl.coderslab.webcrawler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.webcrawler.entity.Email;
import pl.coderslab.webcrawler.entity.Keywords;
import pl.coderslab.webcrawler.entity.Url;
import pl.coderslab.webcrawler.email.LinkExtractor;
import pl.coderslab.webcrawler.email.EmailExtractor;
import pl.coderslab.webcrawler.repository.EmailRepository;
import pl.coderslab.webcrawler.repository.KeywordsRepository;
import pl.coderslab.webcrawler.repository.UrlRepository;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Collection;
import java.util.List;

@Controller
public class KeywordsController {
    private final EmailRepository emailRepository;
    private final UrlRepository urlRepository;
    private final KeywordsRepository keywordsRepository;

    public KeywordsController(EmailRepository emailRepository, UrlRepository urlRepository, KeywordsRepository keywordsRepository) {
        this.emailRepository = emailRepository;
        this.urlRepository = urlRepository;
        this.keywordsRepository = keywordsRepository;
    }
    //    search links by keywords and save
    @RequestMapping("/searchlinks")
    public String searchLinksByKeywords(Model model, @RequestParam String keywords, @RequestParam int numberOfPages) {
        List<String> keywordslist2 = Stream.of(keywords.split(" ")).map(elem -> new String(elem)).collect(Collectors.toList());
        String keywordslist = String.join("+", keywordslist2);
        LinkExtractor linkExtractor = new LinkExtractor();
        List<String> links = linkExtractor.pagesGoogle(numberOfPages, keywordslist);
        //walidacja
        EmailExtractor emailExtractor = new EmailExtractor();
        List<String> validlinks = links.stream().filter(link -> emailExtractor.isValidRelativeURL(link)).collect(Collectors.toList());
        //pobranie linku i wrzucenie do metody wyszukiwania
        List<Url> urlList = validlinks.stream().map(link -> new Url(link)).collect(Collectors.toList());
        List<Email> emailListAll = validlinks.stream().map(link -> emailExtractor.searchEmails(link)).flatMap(Collection::stream)
                .collect(Collectors.toList());
        //bez duplikatów
        List<Email> emailList = emailListAll.stream().distinct().collect(Collectors.toList());

        List<Keywords> keywordList = keywordslist2.stream().map(key -> new Keywords(key, emailList)).collect(Collectors.toList());
        model.addAttribute("emails", emailList);
        if (!emailList.isEmpty()) {
            this.urlRepository.saveAll(urlList);
            this.emailRepository.saveAll(emailList);
            this.keywordsRepository.saveAll(keywordList);
        }
        return "searchkeywords";
    }
}
