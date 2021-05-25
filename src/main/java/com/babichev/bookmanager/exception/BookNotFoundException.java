package com.babichev.bookmanager.exception;

/**
 * An exception for cases, when a book is null or a book with ID doesn't exist into the database
 */
public class BookNotFoundException extends Throwable {

    public BookNotFoundException() {
    }

    public BookNotFoundException(String message) {
        super(message);
    }
}
