package pl.coderslab.webcrawler.controller;

import ch.qos.logback.classic.Logger;
import org.hibernate.mapping.Collection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.webcrawler.email.EmailExtractor;
import pl.coderslab.webcrawler.entity.Email;
import pl.coderslab.webcrawler.entity.Keywords;
import pl.coderslab.webcrawler.entity.Url;
import pl.coderslab.webcrawler.repository.EmailRepository;
import pl.coderslab.webcrawler.repository.KeywordsRepository;
import pl.coderslab.webcrawler.repository.UrlRepository;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
//@RequestMapping("/email")
public class UrlController {
    private final EmailRepository emailRepository;
    private final UrlRepository urlRepository;
    private final KeywordsRepository keywordsRepository;

    public UrlController(EmailRepository emailRepository, UrlRepository urlRepository, KeywordsRepository keywordsRepository) {
        this.emailRepository = emailRepository;
        this.urlRepository = urlRepository;
        this.keywordsRepository = keywordsRepository;
    }

    @GetMapping("/")
    public String mainForm() {
        return "mainform";
    }

    @RequestMapping("/listall") //z db
    public String listAllEmail(Model model) {
        List<Email> emails = emailRepository.findAll();
        model.addAttribute("emails", emails);
        return "listall";
    }

    @RequestMapping("/list") //z db
    public String listEmail(Model model) {
        List<Email> emails = emailRepository.findAllByEmails();
        model.addAttribute("emails", emails);
        return "list";
    }

    @RequestMapping("/keywords")
    public String filterByKeyword(Model model) {
//        List<Keywords> keywords = keywordsRepository.findAll();
        List<Keywords> keywords = keywordsRepository.findDistinctByKeywords();
//        List<Keywords> keywords = keywordsRepository.findlByEmails();
        model.addAttribute("keywords", keywords);
//        model.addAttribute("emails", emails);
        return "keywords";
    }

    //logger test-model
    Logger logger = null;

    @RequestMapping(value = "/test-model")
    @ResponseBody
    public void getAllFromMap(Model model) {
        List<Email> emails = emailRepository.findAllByEmails();
        model.addAttribute("emails", emails);
        model.asMap().forEach((k, v) -> logger.debug(k + " : " + v));
    }

    @GetMapping("/form")
    public String showMainFormSearch() {
        return "form";
    }

    //    search and save
    @RequestMapping("/search")
    public String searchListEmail(Model model, @RequestParam String url) {
        EmailExtractor emailExtractor = new EmailExtractor();
//       walidacja
        if (!emailExtractor.isValidRelativeURL(url)) {
            return "redirect:/form";
        }
        Set<String> emails = emailExtractor.searchEmailsByUrl(url);
        model.addAttribute("url", url);
        model.addAttribute("emails", emails);
         List<Email> emailList = emails.stream().map(mail -> new Email(mail)).collect(Collectors.toList());

        this.emailRepository.saveAll(emailList);
//        this.emailRepository.save(new Email(mail, url));
        this.urlRepository.save(new Url(url, emailList));

//            Url url1 = new Url(url, emailList);
//            for (Email em : emailList) {
//                em.addUrl(url1);
//                this.urlRepository.save(url1);
//            }
        return "search";
    }

    @RequestMapping("/email/delete/{id}")
    public String deleteEmail(@PathVariable Long id) {
        emailRepository.deleteById(id);
        return "redirect:/listall";
    }

    @RequestMapping("/keyword/delete/{id}")
    public String deleteKeyword(@PathVariable Long id) {
        keywordsRepository.deleteById(id);
        return "redirect:/keywords";
    }
}
