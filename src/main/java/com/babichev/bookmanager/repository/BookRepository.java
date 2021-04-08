package com.babichev.bookmanager.repository;

import com.babichev.bookmanager.entity.Book;

import java.util.*;

public interface BookRepository {
    Book add(Book book);

    void remove(int id);

    Book get(int id);

    List<Book> getAll();
}
