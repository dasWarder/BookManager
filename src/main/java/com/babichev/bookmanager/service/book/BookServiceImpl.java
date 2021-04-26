package com.babichev.bookmanager.service.book;

import com.babichev.bookmanager.repository.book.BookRepository;
import com.babichev.bookmanager.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;


@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookDao) {
        this.bookRepository = bookDao;
    }

    @Override
    @Transactional
    public Book addBook(Book book, int customerId) {
        Assert.notNull(book, "book must not be null");
        return bookRepository.add(book, customerId);
    }

    @Override
    @Transactional
    public void removeBook(int id, int customerId) {
        bookRepository.remove(id, customerId);
    }

    @Override
    public Book getBookById(int id, int customerId) {
        return bookRepository.get(id, customerId);
    }

    @Override
    public List<Book> getAll(int customerId) {
        return bookRepository.getAll(customerId);
    }

    @Override
    public List<Book> getSorted(String sortBy, int customerId) {
        return bookRepository.getSortedByParam(sortBy, customerId);
    }
}
