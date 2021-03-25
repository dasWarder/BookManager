package com.babichev.bookmanager.repository;

import com.babichev.bookmanager.entity.Book;
import java.util.*;

public interface BookDao {
    Book addBook(Book book);

    void removeBook(int id);

    Book getBookById(int id);

    List<Book> getAll();
}
