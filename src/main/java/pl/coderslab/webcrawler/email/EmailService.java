package pl.coderslab.webcrawler.email;

import org.springframework.stereotype.Service;
import pl.coderslab.webcrawler.repository.EmailRepository;

@Service
public class EmailService {
    private final EmailRepository emailRepository;

    public EmailService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }
}

