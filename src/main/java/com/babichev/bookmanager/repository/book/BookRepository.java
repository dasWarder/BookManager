package com.babichev.bookmanager.repository.book;

import com.babichev.bookmanager.entity.Book;

import java.util.*;

public interface BookRepository {
    Book add(Book book, int customerId);

    void remove(int id, int customerId);

    Book get(int id, int customerId);

    List<Book> getAll(int customerId);

    List<Book> getAllSortedByYear(int customerId);

    List<Book> getAllSortedByName(int customerId);

    List<Book> getAllSortedByAuthor(int customerId);
}
