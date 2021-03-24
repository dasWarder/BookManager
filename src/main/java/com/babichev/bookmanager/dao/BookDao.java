package com.babichev.bookmanager.dao;

import com.babichev.bookmanager.entity.Book;
import java.util.*;

public interface BookDao {
    void addBook(Book book);

    void updateBook(Book book);

    void removeBook(long id);

    Book getBookById(long id);

    List<Book> getAll();
}
