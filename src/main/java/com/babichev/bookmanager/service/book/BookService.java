package com.babichev.bookmanager.service.book;

import com.babichev.bookmanager.entity.Book;
import com.babichev.bookmanager.exception.BookNotFoundException;

import java.util.List;

/**
 * An interface that describes a behavior of a class on the service layer (between the controller and the repository)
 */
public interface BookService {

    /**
     * The method to validate a book object for storage it to the database
     * @param book a book object that must be validated
     * @param customerId the ID of a customer which one the book must belong
     * @return the book object with ID stored to the database
     */
    Book addBook(Book book, int customerId);

    /**
     * The method to validate a book object for removing from the database
     * @param id the ID of a book that must be removed from the database
     * @param customerId the ID of a customer which one the book must belong
     */
    void removeBook(int id, int customerId);

    /**
     * The method to validate a book object for receiving it from the database
     * @param id the ID of a book that must be received from the database
     * @param customerId the ID of a customer which one the book must belong
     * @return the book object from the database
     */
    Book getBookById(int id, int customerId);

    /**
     * The method to validate a list of all book objects for a customer
     * @param customerId the ID of a customer which one the collection of books must belong
     * @return the list of books for the customer from the database
     */
    List<Book> getAll(int customerId);

    /**
     * The method to validate a list of sorted book objects by a parameter
     * @param sortBy the parameter for sorting the books
     * @param customerId the ID of a customer which one the list of books must belong
     * @return the sorted list of books from the database
     */
    List<Book> getSorted(String sortBy, int customerId) throws BookNotFoundException;
}
