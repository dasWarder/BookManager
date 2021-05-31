package com.babichev.bookmanager.repository.book;

import com.babichev.bookmanager.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


/**
 * An interface describes the layer between the service and the database for the customer entity
 */
@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    /**
     * The method to remove a book from by its id and customer id
     * @param bookId the id of the book
     * @param customerId the if of the customer which one the book is belong
     */
    @Transactional
    void deleteBookByIdAndCustomer_Id(int bookId, int customerId);

    /**
     * The method to get the book by its id and customer id
     * @param bookId the id of the book
     * @param customerId the if of the customer
     * @return the book object from the database
     */
    Book getBookByIdAndCustomer_Id(int bookId, int customerId);

    /**
     * The method to get pageable list of books by customer id sorted by param
     * @param customerId the id of the customer
     * @param pageable the pageable object
     * @return the page of books
     */
    Page<Book> getBooksByCustomer_Id(int customerId, Pageable pageable);
}
