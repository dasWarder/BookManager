package com.babichev.bookmanager.controller;


import com.babichev.bookmanager.entity.Book;
import com.babichev.bookmanager.entity.Customer;
import com.babichev.bookmanager.exception.BookNotFoundException;
import com.babichev.bookmanager.service.book.BookService;
import com.babichev.bookmanager.service.detail.DetailsService;
import com.babichev.bookmanager.service.parser.DetailsParserService;
import com.babichev.bookmanager.entity.Details;
import com.babichev.bookmanager.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import static java.util.Objects.isNull;

@Controller
@Slf4j
public class BookController {

    private BookService bookService;
    private DetailsParserService parserService;
    private DetailsService detailsService;
    private SecurityUtil securityUtil;

    public BookController(BookService bookService, DetailsParserService parserService, DetailsService detailsService, SecurityUtil securityUtil) {
        this.bookService = bookService;
        this.parserService = parserService;
        this.detailsService = detailsService;
        this.securityUtil = securityUtil;
    }

    @GetMapping(value = "/books")
    public String getAll(@RequestParam(value = "sort", required = false) String sortBy, Model model) throws BookNotFoundException {
        int customerId = securityUtil.getAuthUserId();
        log.info("Get all books for customer {}", customerId);
        List<Book> all = isNull(sortBy)?
                bookService.getAll(customerId) :
                bookService.getSorted(sortBy, customerId);

        model.addAttribute("book", new Book());
        model.addAttribute("books", all);

        return "books";
    }


    @PostMapping(value = "/books/add")
    public String add(@ModelAttribute("book") Book book) {
        int customerId = securityUtil.getAuthUserId();
        log.info("Add a book {} for customer {}", book, customerId);
        Book addedBook = bookService.addBook(book, customerId);

        return "redirect:/books";
    }


    @GetMapping(value = "/books/{id}")
    public String remove(@PathVariable("id") int id) {
        int customerId = securityUtil.getAuthUserId();
         log.info("Remove a book with id {} for customer {}", id, customerId);
         bookService.removeBook(id, customerId);

         return "redirect:/books";
    }

    @GetMapping(value = "/books/update/{id}")
    public String update(@PathVariable("id") int id, Model model) {
        int customerId = securityUtil.getAuthUserId();
        Book book = bookService.getBookById(id, customerId);
        log.info("Update a book {} for customer {}", book, customerId);

        if(!isNull(book.getDetails())) {
            detailsService.remove(book.getDetails().getId(), book.getId());
        }

        model.addAttribute("book", book);

        return "updateForm";
    }

    @GetMapping(value = "/books/book/{id}")
    public String get(@PathVariable("id") int id, Model model) throws BookNotFoundException {
        int customerId = securityUtil.getAuthUserId();
        log.info("Get details for book with id {} for customer with id {}", id, customerId);
        Book book = bookService.getBookById(id, customerId);
        Details information;
        if(!isNull(book)) {
                if(isNull(book.getDetails())) {
                    log.info("Get new details information for a book with id {} for a customer with id {}", book.getId(), customerId);
                    information = parserService.findInfoOnPage(book);
                    detailsService.add(information, book.getId());
                } else {
                    log.info("Get details information with id {} for customer with id {}", book.getDetails().getId(), customerId);
                    information= detailsService.get(book.getDetails().getId(), book.getId());
                }
                model.addAttribute("information", information);
        }

        model.addAttribute("book", book);

        return "bookInfo";
    }
}
