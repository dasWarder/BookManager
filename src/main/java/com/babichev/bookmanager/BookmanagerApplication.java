package com.babichev.bookmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookmanagerApplication {

    public static void main(String[] args) {
//        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(BookmanagerApplication.class);
//
//        DetailsDao detailsDaoImpl = (DetailsDao) configApplicationContext.getBean("detailsDaoImpl");
//        Details add = detailsDaoImpl.add(new Details("TEST", "TEST"), 1);
//        System.out.println(add);

        SpringApplication.run(BookmanagerApplication.class, args);
    }

}
