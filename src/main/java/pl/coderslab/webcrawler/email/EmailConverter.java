package pl.coderslab.webcrawler.email;

import org.springframework.core.convert.converter.Converter;
import pl.coderslab.webcrawler.entity.Email;
import pl.coderslab.webcrawler.repository.EmailRepository;

public class EmailConverter implements Converter<String, Email> {

        private EmailRepository emailRepository;
        @Override
        public Email convert(String source) {
            return (Email) emailRepository.findAll();
        }
    }

