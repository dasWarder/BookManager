package com.babichev.bookmanager.repository;

import com.babichev.bookmanager.entity.Book;

import java.util.*;

public interface BookRepository {
    Book add(Book book, int customerId);

    void remove(int id, int customerId);

    Book get(int id, int customerId);

    List<Book> getAll(int customerId);
}