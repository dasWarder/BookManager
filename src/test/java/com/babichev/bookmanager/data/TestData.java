package com.babichev.bookmanager.data;

import com.babichev.bookmanager.entity.*;

import java.time.LocalDateTime;
import java.util.*;

public class TestData {

    public static final Role USER_ROLE = new Role(100, "USER");

    public static final Customer FIRST_CUSTOMER = new Customer(20, "marrySmith3@gmail.com", "1234567", true);
    public static final Customer SECOND_CUSTOMER = new Customer(21, "warder@gmail.com", "1234567", true);

    public static final Book FIRST_BOOK = new Book(3, "The Hobbit", "J.R.R.Tolkien", 1937);
    public static final Book SECOND_BOOK = new Book(4, "Harry Potter", "Rowling", 1994);
    public static final Book THIRD_BOOK = new Book(5, "The witcher", "Sapkowski", 1986);
    public static final Book FOURTH_BOOK = new Book(6, "War and Peace", "Tolstoy", 1867);
    public static final Book WRONG_ID_BOOK = new Book(10000000, "WRONG", "WRONG", 2000);
    public static final Details FIRST_DETAILS = new Details(7, "THE HOBBIT DESCRIPTION", "THE HOBBIT BOOK COVER");
    public static final Note FIRST_NOTE = new Note(12, LocalDateTime
            .of(2021, 04, 10, 12, 22, 00), "First time I read this book");
    public static final Note SECOND_NOTE = new Note(13, LocalDateTime
            .of(2021, 04, 12, 14, 44, 54), "The second note about this book");


    public static List<Book> books = Arrays.asList(FIRST_BOOK, SECOND_BOOK, THIRD_BOOK);
    public static List<Note> notes = Arrays.asList(FIRST_NOTE, SECOND_NOTE);
    public static final Set<Role> roles = new HashSet(Arrays.asList(USER_ROLE));


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
        return new Customer("newcustomer@gmail.com", "NEWPASSWORD");
    }

    public static Customer updatedCustomer(Customer customer) {
        customer.setLogin("updated@gmail.com");
        customer.setPassword("UPDATEDPASSWORD");

        return customer;
    }

    public static Note createNote() {
        return
                new Note(LocalDateTime
                        .of(2021, 04, 20, 9, 30, 25), "NEW TEXT FOR NOTE");
    }

    public static Note updateNote(Note note) {
        note.setDateAndTime(LocalDateTime.of(2021, 03, 28, 16, 44, 00));
        note.setText("UPDATED TEXT");

        return note;
    }

    public static Role createRole() {
        return new Role("TESTER");
    }

    public static Role updateRole(Role role) {
        role.setName("UPDATED_USER");

        return role;
    }


}
