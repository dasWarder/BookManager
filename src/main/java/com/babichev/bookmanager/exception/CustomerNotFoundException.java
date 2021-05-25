package com.babichev.bookmanager.exception;


/**
 * An exception for cases, when a customer is null or a customer with ID doesn't exist into the database
 */
public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String message) {
        super(message);
    }

    public CustomerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
