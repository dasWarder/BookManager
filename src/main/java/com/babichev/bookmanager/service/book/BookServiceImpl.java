package com.babichev.bookmanager.service.book;

import com.babichev.bookmanager.repository.book.BookRepository;
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
    public Book addBook(Book book, int customerId) {

        return bookRepository.add(book, customerId);
    }

    @Override
    @Transactional
    public void removeBook(int id, int customerId) {

        bookRepository.remove(id, customerId);
    }

    @Override
    public Book getBookById(int id, int customerId) {
        Book book = bookRepository.get(id, customerId);
        return book;
    }

    @Override
    public List<Book> getAll(int customerId) {
        List<Book> all = bookRepository.getAll(customerId);
        return all;
    }
}
