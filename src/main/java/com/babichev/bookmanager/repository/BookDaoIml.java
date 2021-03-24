package com.babichev.bookmanager.repository;

import com.babichev.bookmanager.entity.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class BookDaoIml implements BookDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addBook(Book book) {
        if(book.getId() == 0 && getBookById(book.getId()) == null) {
            entityManager.persist(book);
        } else {
            entityManager.merge(book);
        }
    }

    @Override
    public void removeBook(long id) {
        Book bookById = getBookById(id);

        if(bookById != null) {
            entityManager.remove(bookById);
        }
    }

    @Override
    public Book getBookById(long id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    public List<Book> getAll() {
        Query select_b_from_book_b = entityManager.createQuery(
                "SELECT b FROM Book b"
        );

        return select_b_from_book_b
                .getResultList();
    }
}
