package com.babichev.bookmanager.service;

import com.babichev.bookmanager.entity.Book;

import java.util.List;

public interface BookService {
    Book addBook(Book book);

    void removeBook(int id);

    Book getBookById(int id);

    List<Book> getAll();
}
