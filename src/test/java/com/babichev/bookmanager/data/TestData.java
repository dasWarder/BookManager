package com.babichev.bookmanager.data;

import com.babichev.bookmanager.entity.Book;

import java.util.*;

//('The Hobbit', 'J.R.R.Tolkien', 1937),
//('Harry Potter', 'Rowling', 1994),
//('The witcher', 'Sapkowski', 1986),
//('War and Peace', 'Tolstoy', 1867);

public class TestData {
    public static final Integer UPDATED_ID = 123456;
    public static final Book FIRST_BOOK = new Book(1, "The Hobbit", "J.R.R.Tolkien", 1937);
    public static final Book SECOND_BOOK = new Book(2, "Harry Potter", "Rowling", 1994);
    public static final Book THIRD_BOOK = new Book(3, "The witcher", "Sapkowski", 1986);
    public static final Book FOURTH_BOOK = new Book(4, "War and Peace", "Tolstoy", 1867);
    public static final Book WRONG_ID_BOOK = new Book(10000000, "WRONG", "WRONG", 2000);

    public static List<Book> books = new ArrayList<>();

    static {
        books.add(FIRST_BOOK);
        books.add(SECOND_BOOK);
        books.add(THIRD_BOOK);
        books.add(FOURTH_BOOK);
    }

    public static Book createNew() {
        return new Book(null, "NEW BOOK", "NEW AUTHOR", 2021);
    }

    public static Book updated(Book book) {
        book.setName("UPDATED_BOOK");
        book.setAuthor("UPDATED_AUTHOR");

        return book;
    }
}
