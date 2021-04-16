package com.babichev.bookmanager.controller;


import com.babichev.bookmanager.entity.Book;
import com.babichev.bookmanager.service.book.BookService;
import com.babichev.bookmanager.service.detail.DetailsService;
import com.babichev.bookmanager.service.parser.DetailsParserService;
import com.babichev.bookmanager.entity.Details;
import com.babichev.bookmanager.util.SecurityUtil;
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
        int customerId = SecurityUtil.getAuthUserId();
        List<Book> all = bookService.getAll(customerId);

        model.addAttribute("book", new Book());
        model.addAttribute("books", all);

        return "books";
    }


    @PostMapping(value = "/books/add")
    public String add(@ModelAttribute("book") Book book) {
        int customerId = SecurityUtil.getAuthUserId();

        Book created = bookService.addBook(book, customerId);

        return "redirect:/books";
    }


    @GetMapping(value = "/books/{id}")
    public String remove(@PathVariable("id") int id) {
         int customerId = SecurityUtil.getAuthUserId();

         bookService.removeBook(id, customerId);
         return "redirect:/books";
    }

    @GetMapping(value = "/books/update/{id}")
    public String updateForm(@PathVariable("id") int id, Model model) {
        int customerId = SecurityUtil.getAuthUserId();

        Book book = bookService.getBookById(id, customerId);

        if(book.getDetails() != null) {
            detailsService.remove(book.getDetails().getId(), book.getId());
        }

        model.addAttribute("book", book);

        return "updateForm";
    }

    @GetMapping(value = "/books/book/{id}")
    public String get(@PathVariable("id") int id, Model model) {
        int customerId = SecurityUtil.getAuthUserId();
        Book book = bookService.getBookById(id, customerId);
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
