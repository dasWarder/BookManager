package com.babichev.bookmanager.repository;

import com.babichev.bookmanager.entity.Book;
import com.babichev.bookmanager.entity.Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
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
    @Transactional
    public void removeBook(int id) {
        Book bookById = getBookById(id);

        if(bookById != null) {
            entityManager.remove(bookById);
        }
    }

    @Override
    @Transactional
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
