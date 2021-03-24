package com.babichev.bookmanager.repository;

import com.babichev.bookmanager.entity.Book;
import java.util.*;

public interface BookDao {
    void addBook(Book book);

    void removeBook(long id);

    Book getBookById(long id);

    List<Book> getAll();
}
