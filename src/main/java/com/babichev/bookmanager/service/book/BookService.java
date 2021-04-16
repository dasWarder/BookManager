package com.babichev.bookmanager.service.book;

import com.babichev.bookmanager.entity.Book;

import java.util.List;

public interface BookService {
    Book addBook(Book book, int customerId);

    void removeBook(int id, int customerId);

    Book getBookById(int id, int customerId);

    List<Book> getAll(int customerId);
}
