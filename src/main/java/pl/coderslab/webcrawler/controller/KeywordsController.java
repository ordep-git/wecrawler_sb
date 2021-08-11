package pl.coderslab.webcrawler.controller;

import ch.qos.logback.classic.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    private static final String GOOGLE_PRE = "https://www.google.com/search?q=email+";
    private final EmailRepository emailRepository;
    private final UrlRepository urlRepository;
    private final KeywordsRepository keywordsRepository;
    Logger logger = null;
//    Set<String> emails = new HashSet<>();

    public KeywordsController(EmailRepository emailRepository, UrlRepository urlRepository, KeywordsRepository keywordsRepository) {
        this.emailRepository = emailRepository;
        this.urlRepository = urlRepository;
        this.keywordsRepository = keywordsRepository;
    }

    //logger test-model
//    @RequestMapping(value = "/test-email")
//    @ResponseBody
//    public void getAllFromMap(Model model) {
//        List<Email> emails = emailRepository.findAllByEmails();
//        model.addAttribute("emails", emails);
//        model.asMap().forEach((k, v) -> logger.debug(k + " : " + v));
//    }

    //    search links by keywords and save


    @RequestMapping("/searchlinks")
    public String searchLinksByKeywords(Model model, @RequestParam String keywords) {
        List<String> keywordslist2 = Stream.of(keywords.split(" ")).map(elem -> new String(elem)).collect(Collectors.toList());
        String keywordslist = String.join("+", keywordslist2);
        String urlgoogle = GOOGLE_PRE + keywordslist;
        LinkExtractor linkExtractor = new LinkExtractor();
        List<String> links = linkExtractor.searchLinks(urlgoogle);
        EmailExtractor emailExtractor = new EmailExtractor();
        //walidacja
        List<String> validlinks = links.stream().filter(link -> emailExtractor.isValidRelativeURL(link)).collect(Collectors.toList());

        //pobranie linku i wrzucenie do metody wyszukiwania
        List<Url> urlList = validlinks.stream().map(link -> new Url(link)).collect(Collectors.toList());

        List<Email> emailList = validlinks.stream().map(link -> emailExtractor.searchEmails(link)).flatMap(Collection::stream)
                .collect(Collectors.toList());

        List<Keywords> keywordList = keywordslist2.stream().map(key -> new Keywords(key, emailList)).collect(Collectors.toList());

        model.addAttribute("emails", emailList);
        this.urlRepository.saveAll(urlList);
        this.emailRepository.saveAll(emailList);
        this.keywordsRepository.saveAll(keywordList);



//        for (Email em : emailList) {
//            Url url1 = new Url(urlgoogle, emailList);
//            em.addUrl(url1);
//        }

        return "searchkeywords";
    }




//    @ModelAttribute("keywords")
    @PostMapping("/searchlinks")
    public String keywords(Keywords keywords, Model model) {
        model.addAttribute("keywords", keywords.getKeyword());
        return "searchkeywords";
    }
}
