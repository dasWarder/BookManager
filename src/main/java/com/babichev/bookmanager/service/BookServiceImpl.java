package com.babichev.bookmanager.service;

import com.babichev.bookmanager.repository.BookRepository;
import com.babichev.bookmanager.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookDao) {
        this.bookRepository = bookDao;
    }

    @Override
    @Transactional
    public Book addBook(Book book) {
        return bookRepository.add(book);
    }

    @Override
    @Transactional
    public void removeBook(int id) {
        bookRepository.remove(id);
    }

    @Override
    public Book getBookById(int id) {
        Book book = bookRepository.get(id);
        return book;
    }

    @Override
    public List<Book> getAll() {
        List<Book> all = bookRepository.getAll();
        return all;
    }
}
