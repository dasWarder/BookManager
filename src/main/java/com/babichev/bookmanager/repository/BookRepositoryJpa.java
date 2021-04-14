package com.babichev.bookmanager.repository;

import com.babichev.bookmanager.entity.Book;
import com.babichev.bookmanager.entity.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public Book add(Book book) {

        if(book.getId() == null) {
            em.persist(book);
            return book;
        } else if (get(book.getId()) == null){
            return null;
        }

        return em.merge(book);
    }

    @Override
    @Transactional
    public void remove(int id) {
        Book bookById = get(id);

        if(bookById != null) {
            em.remove(bookById);
        }
    }

    @Override
    @Transactional
    public Book get(int id) {
        return em.find(Book.class, id);
    }

    @Override
    public List<Book> getAll() {
        Query select_b_from_book_b = em.createQuery(
                "SELECT b FROM Book b"
        );

        return select_b_from_book_b
                .getResultList();
    }
}
