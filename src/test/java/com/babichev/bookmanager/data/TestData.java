package com.babichev.bookmanager.data;

import com.babichev.bookmanager.entity.Book;
import com.babichev.bookmanager.entity.Customer;
import com.babichev.bookmanager.entity.Details;

import java.util.*;

//('The Hobbit', 'J.R.R.Tolkien', 1937),
//('Harry Potter', 'Rowling', 1994),
//('The witcher', 'Sapkowski', 1986),
//('War and Peace', 'Tolstoy', 1867);

//(1, 'marrySmith3', '12345'),
//(2, 'warder', '12345');

public class TestData {
    public static final Customer FIRST_CUSTOMER = new Customer(20, "marrySmith3", "12345");
    public static final Customer SECOND_CUSTOMER = new Customer(21, "warder", "12345");
    public static final Book FIRST_BOOK = new Book(3, "The Hobbit", "J.R.R.Tolkien", 1937);
    public static final Book SECOND_BOOK = new Book(4, "Harry Potter", "Rowling", 1994);
    public static final Book THIRD_BOOK = new Book(5, "The witcher", "Sapkowski", 1986);
    public static final Book FOURTH_BOOK = new Book(6, "War and Peace", "Tolstoy", 1867);
    public static final Book WRONG_ID_BOOK = new Book(10000000, "WRONG", "WRONG", 2000);
    public static final Details FIRST_DETAILS = new Details(7, "THE HOBBIT DESCRIPTION", "THE HOBBIT BOOK COVER");

    public static List<Book> books = new ArrayList<>();

    static {
        books.add(FIRST_BOOK);
        books.add(SECOND_BOOK);
        books.add(THIRD_BOOK);
        books.add(FOURTH_BOOK);
    }

    public static Book createBook() {
        return new Book(null, "NEW BOOK", "NEW AUTHOR", 2021);
    }

    public static Book updatedBook(Book book) {
        book.setName("UPDATED BOOK");
        book.setAuthor("UPDATED AUTHOR");

        return book;
    }

    public static Details createDetails() {
        return new Details("NEW DESCRIPTION", "NEW IMAGE");
    }

    public static Details updatedDetails(Details details) {
        details.setDescription("UPDATED DESCRIPTION");
        details.setImage("UPDATED IMAGE");

        return details;
    }

    public static Customer createCustomer() {
        return new Customer("NEW CUSTOMER", "NEW PASSWORD");
    }

    public static Customer updatedCustomer(Customer customer) {
        customer.setLogin("UPDATED LOGIN");
        customer.setPassword("UPDATED PASSWORD");

        return customer;
    }


}
