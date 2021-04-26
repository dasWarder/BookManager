package com.babichev.bookmanager.exception;

public class BookNotFoundException extends Throwable {

    public BookNotFoundException() {
    }

    public BookNotFoundException(String message) {
        super(message);
    }
}
