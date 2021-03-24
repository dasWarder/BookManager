package com.babichev.bookmanager.controller;


import com.babichev.bookmanager.dao.BookDao;
import com.babichev.bookmanager.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
public class BookController {

    private BookDao bookDao;

    @Autowired
    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @GetMapping(value = "/books")
    public String listBooks(Model model) {
        List<Book> all = bookDao.getAll();

        model.addAttribute("book", new Book());
        model.addAttribute("books", all);

        return "books";
    }


    @PostMapping(value = "/books/add")
    public String addBook(@ModelAttribute("book") Book book) {

        if(book.getId() == 0) {
            bookDao.addBook(book);
        } else {
            bookDao.updateBook(book);
        }

        return "redirect:/books";
    }

    @GetMapping(value = "/books/remove")
    public String removeBook(@RequestParam("id") long id) {
         bookDao.removeBook(id);

         return "redirect:/books";
    }
}
