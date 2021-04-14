package com.babichev.bookmanager.controller;


import com.babichev.bookmanager.entity.Book;
import com.babichev.bookmanager.service.BookService;
import com.babichev.bookmanager.service.DetailsService;
import com.babichev.bookmanager.service.DetailsParserService;
import com.babichev.bookmanager.entity.Details;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {

    private BookService bookService;
    private DetailsParserService parserService;
    private DetailsService detailsService;

    public BookController(BookService bookService, DetailsParserService parserService, DetailsService detailsService) {
        this.bookService = bookService;
        this.parserService = parserService;
        this.detailsService = detailsService;
    }

    @GetMapping(value = "/books")
    public String getAll(Model model) {
        List<Book> all = bookService.getAll();

        model.addAttribute("book", new Book());
        model.addAttribute("books", all);

        return "books";
    }


    @PostMapping(value = "/books/add")
    public String add(@ModelAttribute("book") Book book) {

        Book created = bookService.addBook(book);

        return "redirect:/books";
    }


    @GetMapping(value = "/books/{id}")
    public String remove(@PathVariable("id") int id) {
         bookService.removeBook(id);
         return "redirect:/books";
    }

    @GetMapping(value = "/books/update/{id}")
    public String updateForm(@PathVariable("id") int id, Model model) {
        Book book = bookService.getBookById(id);

        if(book.getDetails() != null) {
            detailsService.remove(book.getDetails().getId(), book.getId());
        }

        model.addAttribute("book", book);

        return "updateForm";
    }

    @GetMapping(value = "/books/book/{id}")
    public String get(@PathVariable("id") int id, Model model) {
        Book book = bookService.getBookById(id);
        Details information;

        if(book != null) {
                if(book.getDetails() == null) {
                    information = parserService.findInfoOnPage(book);
                    detailsService.add(information, book.getId());
                } else {
                    information= detailsService.get(book.getDetails().getId(), book.getId());
                }
                model.addAttribute("information", information);
        }

        model.addAttribute("book", book);

        return "bookInfo";
    }
}
