package com.babichev.bookmanager.repository.book;

import com.babichev.bookmanager.entity.Book;
import com.babichev.bookmanager.entity.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

import static java.util.Objects.isNull;


/**
 * An implementation of BookRepository interface
 */
@Repository
public class BookRepositoryJpa implements BookRepository {

    /**
     * An object of entity manager to interact with the database
     */
    @PersistenceContext
    private EntityManager em;


    /**
     * The method that implements to save method
     * @param book an entity of the book, that must be stored to the database
     * @param customerId the ID of a customer which one the book is belong
     * @return the object of a book from the database
     */
    @Override
    @Transactional
    public Book add(Book book, int customerId) {
        /**
         * @see EntityManager#getReference(Class, Object) 
         */
        book.setCustomer(em.getReference(Customer.class, customerId));

        if(isNull(book.getId())) {
            /**
             * @see EntityManager#persist(Object) 
             */
            em.persist(book);
            return book;
        } else if (isNull(get(book.getId(), customerId))){
            return null;
        }

        /**
         * @see EntityManager#merge(Object) 
         */
        return em.merge(book);
    }

    /**
     * The method that implements to remove command
     * @param id the ID of a book that must be removed from the database
     * @param customerId the ID of a customer which one the book is belong
     */
    @Override
    @Transactional
    public void remove(int id, int customerId) {
        /**
         * @see EntityManager#createQuery(String) 
         */
        Query query = em.createQuery("DELETE FROM Book b WHERE b.id=:id AND b.customer.id=:customerId")
                .setParameter("id", id)
                .setParameter("customerId", customerId);

        query.executeUpdate();
    }

    /**
     * The method that implements to get command
     * @param id the ID of a book that must be received from the database
     * @param customerId the ID of the customer which one book is belong
     * @return the object of a book from the database
     */
    @Override
    @Transactional
    public Book get(int id, int customerId) {
        /**
         * @see EntityManager#find(Class, Object) 
         */
        Book book = em.find(Book.class, id);

        return !isNull(book)
                && book.getCustomer().getId() == customerId? book : null;
    }

    /**
     * The method that implements to getAll command
     * @param customerId the ID of a customer which one the books are belong
     * @return the list of books from the database
     */
    @Override
    public List<Book> getAll(int customerId) {
        /**
         * @see EntityManager#createQuery(String) 
         */
        Query select_b_from_book_b = em.createQuery(
                "SELECT b FROM Book b LEFT JOIN FETCH b.details WHERE b.customer.id=:customerId")
                .setParameter("customerId", customerId);

        return select_b_from_book_b
                .getResultList();
    }

    /**
     * The method that implements to getSortedByParam command
     * @param sortBy a key param for the sorting
     * @param customerId the ID of a customer
     * @return the List of sorting by the param books from the database
     */
    @Override
    public List<Book> getSortedByParam(String sortBy, int customerId) {
        String generalQuery = "SELECT b FROM Book b WHERE b.customer.id=:customerId ORDER BY b.";
        Query finalQuery = null;

        /**
         * @see EntityManager#createQuery(String) 
         */
        if(("name").equals(sortBy)) {
            finalQuery = em.createQuery(generalQuery + "name");
        } else if (("author").equals(sortBy)) {
            finalQuery = em.createQuery(generalQuery + "author");
        } else if (("year").equals(sortBy)) {
            finalQuery = em.createQuery(generalQuery + "year");
        }

        finalQuery.setParameter("customerId", customerId);

        return finalQuery.getResultList();
    }
}
