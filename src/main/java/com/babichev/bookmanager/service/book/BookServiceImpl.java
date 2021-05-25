package com.babichev.bookmanager.service.book;

import com.babichev.bookmanager.repository.book.BookRepository;
import com.babichev.bookmanager.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;


/**
 * The service class that implements BookService interface
 */
@Service
public class BookServiceImpl implements BookService {

    /**
     * The field with a book repository bean
     * @see BookRepository
     */
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookDao) {
        this.bookRepository = bookDao;
    }

    /**
     * The method to validate to save command
     * @param book a book object that must be validated
     * @param customerId the ID of a customer which one the book must belong
     * @return the book object received from the repository layer
     */
    @Override
    @Transactional
    public Book addBook(Book book, int customerId) {
        Assert.notNull(book, "book must not be null");

        /**
         * @see BookRepository#add(Book, int)
         */
        return bookRepository.add(book, customerId);
    }

    /**
     * The method to validate to remove command
     * @param id the ID of a book that must be removed from the database
     * @param customerId the ID of a customer which one the book must belong
     */
    @Override
    @Transactional
    public void removeBook(int id, int customerId) {
        /**
         * @see BookRepository#remove(int, int)
         */
        bookRepository.remove(id, customerId);
    }

    /**
     * The method to validate to getById command
     * @param id the ID of a book that must be received from the database
     * @param customerId the ID of a customer which one the book must belong
     * @return the book object from the database
     */
    @Override
    public Book getBookById(int id, int customerId) {
        /**
         * @see BookRepository#get(int, int)
         */
        return bookRepository.get(id, customerId);
    }

    /**
     * The method to validate getAll command
     * @param customerId the ID of a customer which one the collection of books must belong
     * @return the list of all book objects from the database
     */
    @Override
    public List<Book> getAll(int customerId) {

        /**
         * @see BookRepository#getAll(int)
         */
        return bookRepository.getAll(customerId);
    }

    /**
     * The method to validate getSorted command
     * @param sortBy the parameter for sorting the books
     * @param customerId the ID of a customer which one the list of books must belong
     * @return the list of sorted by the param book objects
     */
    @Override
    public List<Book> getSorted(String sortBy, int customerId) {

        /**
         * @see BookRepository#getSortedByParam(String, int)
         */
        return bookRepository.getSortedByParam(sortBy, customerId);
    }
}
