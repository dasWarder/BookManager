package com.babichev.bookmanager.repository.book;

import com.babichev.bookmanager.entity.Book;

import java.util.*;


/**
 * An interface describes the layer between the service and the database for the customer entity
 */
public interface BookRepository {

    /**
     * The method to save the book to the database
     * @param book an entity of the book, that must be stored to the database
     * @param customerId the ID of a customer which one the book is belong
     * @return a book object with id, stored to the database
     */
    Book add(Book book, int customerId);

    /**
     * The method to remove the book from the database
     * @param id the ID of a book that must be removed from the database
     * @param customerId the ID of a customer which one the book is belong
     */
    void remove(int id, int customerId);

    /**
     * The method to get the book from the database
     * @param id the ID of a book that must be received from the database
     * @param customerId the ID of the customer which one book is belong
     * @return the book from the database
     */
    Book get(int id, int customerId);

    /**
     * The method to getAll books from the database
     * @param customerId the ID of a customer which one the books are belong
     * @return the list of the books from the database
     */
    List<Book> getAll(int customerId);

    /**
     * The method to get all sorted by the param books from the database
     * @param sorBy a key param for the sorting
     * @param customerId the ID of a customer
     * @return the list of the books sorted by @param sortBy
     */
    List<Book> getSortedByParam(String sorBy, int customerId);
}
