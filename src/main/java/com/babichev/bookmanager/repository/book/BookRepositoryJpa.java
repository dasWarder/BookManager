package com.babichev.bookmanager.repository.book;

import com.babichev.bookmanager.entity.Book;
import com.babichev.bookmanager.entity.Customer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

import static java.util.Objects.isNull;

@Repository
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public Book add(Book book, int customerId) {
        book.setCustomer(em.getReference(Customer.class, customerId));

        if(isNull(book.getId())) {
            em.persist(book);
            return book;
        } else if (isNull(get(book.getId(), customerId))){
            return null;
        }

        return em.merge(book);
    }

    @Override
    @Transactional
    public void remove(int id, int customerId) {
        Query query = em.createQuery("DELETE FROM Book b WHERE b.id=:id AND b.customer.id=:customerId")
                .setParameter("id", id)
                .setParameter("customerId", customerId);

        query.executeUpdate();
    }

    @Override
    @Transactional
    public Book get(int id, int customerId) {
        Book book = em.find(Book.class, id);

        return !isNull(book)
                && book.getCustomer().getId() == customerId? book : null;
    }

    @Override
    public List<Book> getAll(int customerId) {
        Query select_b_from_book_b = em.createQuery(
                "SELECT b FROM Book b LEFT JOIN FETCH b.details WHERE b.customer.id=:customerId")
                .setParameter("customerId", customerId);

        return select_b_from_book_b
                .getResultList();
    }

    @Override
    public List<Book> getSortedByParam(String sortBy, int customerId) {
        String generalQuery = "SELECT b FROM Book b WHERE b.customer.id=:customerId ORDER BY b.";
        Query finalQuery = null;

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
