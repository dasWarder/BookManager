package com.babichev.bookmanager.service;

import com.babichev.bookmanager.dao.BookDao;
import com.babichev.bookmanager.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void addBook(Book book) {
        if(book.getId() == 0) {
            bookDao.addBook(book);
        } else {
            bookDao.updateBook(book);
        }
    }

    @Override
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
