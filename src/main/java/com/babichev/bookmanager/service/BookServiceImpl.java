package com.babichev.bookmanager.service;

import com.babichev.bookmanager.repository.BookDao;
import com.babichev.bookmanager.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    @Transactional
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    @Transactional
    public void removeBook(long id) {
        bookDao.removeBook(id);
    }

    @Override
    public Book getBookById(long id) {
        Book book = bookDao.getBookById(id);
        return book;
    }

    @Override
    public List<Book> getAll() {
        List<Book> all = bookDao.getAll();
        return all;
    }
}
