package com.babichev.bookmanager;

import com.babichev.bookmanager.entity.Book;
import com.babichev.bookmanager.service.InfoParserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class BookmanagerApplication {

    public static void main(String[] args) {
//        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(BookmanagerApplication.class);
//
//        InfoParserService infoParserService = (InfoParserService) configApplicationContext.getBean("bookInfoParserService");
//
//        infoParserService.findInfoOnPage(new Book("The Hobbit", "Tolkien", null));

        SpringApplication.run(BookmanagerApplication.class, args);


    }

}
