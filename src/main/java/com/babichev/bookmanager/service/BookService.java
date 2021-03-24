package com.babichev.bookmanager.service;

import com.babichev.bookmanager.entity.Book;

import java.util.List;

public interface BookService {
    void addBook(Book book);

    void removeBook(long id);

    Book getBookById(long id);

    List<Book> getAll();
}
