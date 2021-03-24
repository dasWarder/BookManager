package com.babichev.bookmanager.controller;


import com.babichev.bookmanager.dao.BookDao;
import com.babichev.bookmanager.entity.Book;
import com.babichev.bookmanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
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
        bookService.addBook(book);

        return "redirect:/books";
    }

    @GetMapping(value = "/books/{id}")
    public String remove(@PathVariable("id") long id) {
         bookService.removeBook(id);
         return "redirect:/books";
    }

    @GetMapping(value = "/books/update/{id}")
    public String updateForm(@PathVariable("id") int id, Model model) {
        Book book = bookService.getBookById(id);

        model.addAttribute("book", book);

        return "updateForm";
    }

    @GetMapping(value = "/books/book/{id}")
    public String get(@PathVariable("id") int id, Model model) {
        Book book = bookService.getBookById(id);

        model.addAttribute("book", book);

        return "bookInfo";
    }
}
