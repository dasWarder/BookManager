package com.babichev.bookmanager.exception;


/**
 * An exception for cases, when a role is null or a role with ID doesn't exist into the database
 */
public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(String message) {
        super(message);
    }

    public RoleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
