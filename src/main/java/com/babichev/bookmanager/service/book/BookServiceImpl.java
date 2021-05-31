package com.babichev.bookmanager.service.book;

import com.babichev.bookmanager.entity.Customer;
import com.babichev.bookmanager.exception.BookNotFoundException;
import com.babichev.bookmanager.exception.CustomerNotFoundException;
import com.babichev.bookmanager.repository.book.BookRepository;
import com.babichev.bookmanager.entity.Book;
import com.babichev.bookmanager.repository.customer.CustomerRepository;
import com.babichev.bookmanager.service.AbstractService;
import com.babichev.bookmanager.util.SecurityUtil;
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
public class BookServiceImpl extends AbstractService implements BookService {

    /**
     * The field with a customer repository bean
     * @see CustomerRepository
     */
    private final CustomerRepository customerRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, SecurityUtil securityUtil, CustomerRepository customerRepository) {
        super(bookRepository, securityUtil);
        this.customerRepository = customerRepository;
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
        Customer customerFroDb = customerRepository.getCustomerById(customerId);

        Assert.notNull(book, "book must not be null");
        Assert.notNull(customerFroDb, "The customer must be not NULL");

        if(book.getCustomer() == null) {
            /**
             * @see BookRepository#add(Book, int)
             */
            book.setCustomer(customerFroDb);
            bookRepository.save(book);
        }

        Customer customer = book.getCustomer();

        if(customer.getId() == customerId) {
            return bookRepository.save(book);
        }

        throw new CustomerNotFoundException("The customer with wrong id");
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
        bookRepository.deleteBookByIdAndCustomer_Id(id, customerId);
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
        return bookRepository.getBookByIdAndCustomer_Id(id, customerId);
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
        return bookRepository.getBooksByCustomer_Id(customerId);
    }

    /**
     * The method to validate getSorted command
     * @param sortBy the parameter for sorting the books
     * @param customerId the ID of a customer which one the list of books must belong
     * @return the list of sorted by the param book objects
     */
    @Override
    public List<Book> getSorted(String sortBy, int customerId) throws BookNotFoundException {

        /**
         * @see BookRepository#getSortedByParam(String, int)
         */
        if(sortBy.equals("name")) {
            return bookRepository.getBooksByCustomer_IdOrderByName(customerId);
        } else if (sortBy.equals("year")) {
            return bookRepository.getBooksByCustomer_IdOrderByYear(customerId);
        } else if (sortBy.equals("author")) {
            return bookRepository.getBooksByCustomer_IdOrderByAuthor(customerId);
        }

        throw new BookNotFoundException("There is no books");
    }
}
