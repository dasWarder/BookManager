package com.babichev.bookmanager.repository.book;

import com.babichev.bookmanager.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.*;


/**
 * An interface describes the layer between the service and the database for the customer entity
 */
@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    @Transactional
    void deleteBookByIdAndCustomer_Id(int bookId, int customerId);

    Book getBookByIdAndCustomer_Id(int bookId, int customerId);

    List<Book> getBooksByCustomer_Id(int customerId);

    List<Book> getBooksByCustomer_IdOrderByAuthor( int customerId);

    List<Book> getBooksByCustomer_IdOrderByYear(int customerId);

    List<Book> getBooksByCustomer_IdOrderByName(int customerId);
}
