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
    public Book addBook(Book book) {
        if(book.getId() == null) {
            entityManager.persist(book);
            return book;
        } else if (getBookById(book.getId()) == null){
            return null;
        }

        return entityManager.merge(book);
    }

    @Override
    public void removeBook(int id) {
        Book bookById = getBookById(id);

        if(bookById != null) {
            entityManager.remove(bookById);
        }
    }

    @Override
    public Book getBookById(int id) {
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
