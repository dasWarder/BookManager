package com.babichev.bookmanager.repository;

import com.babichev.bookmanager.data.TestData;
import com.babichev.bookmanager.entity.Book;
import com.babichev.bookmanager.repository.book.BookRepository;
import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = {"/db/clearDb.sql", "/db/populateDb.sql"})
public class BookRepositoryJpaTest {

    @Autowired
    private BookRepository bookRepository;



    @Test
    public void getById() {
        int customerId = TestData.FIRST_CUSTOMER.getId();

        Book bookById = bookRepository.get(TestData.FIRST_BOOK.getId(), customerId);

        assertThat(bookById).usingRecursiveComparison()
                .ignoringFields("details", "customer", "notes")
                .isEqualTo(TestData.FIRST_BOOK);
    }

    @Test
    public void getByWrongId() {
        int customerId = TestData.FIRST_CUSTOMER.getId();

        Book bookById = bookRepository.get(TestData.WRONG_ID_BOOK.getId(), customerId);

        Assert.assertEquals(null, bookById);
    }

    @Test
    public void add() {
        int customerId = TestData.FIRST_CUSTOMER.getId();

        Book saved = bookRepository.add(TestData.createBook(), customerId);
        int id = saved.getId();
        Book mockBookWithId = TestData.createBook();
        mockBookWithId.setId(id);

        assertThat(bookRepository.get(id, customerId))
                .usingRecursiveComparison()
                .ignoringFields("details", "customer", "notes")
                .isEqualTo(mockBookWithId);
    }

    @Test
    public void update() {
        int customerId = TestData.FIRST_CUSTOMER.getId();

        Book added = bookRepository.get(TestData.FIRST_BOOK.getId(), customerId);
        int id = added.getId();
        Book forUpdate = TestData.updatedBook(added);
        forUpdate = bookRepository.add(forUpdate, customerId);

        assertThat(forUpdate)
                .usingRecursiveComparison()
                .ignoringFields("details", "customer")
                .isEqualTo(bookRepository.get(id, customerId));
    }


    @Test
    public void remove() {
        int bookId = TestData.FOURTH_BOOK.getId();
        int customerId = TestData.FIRST_CUSTOMER.getId();

        bookRepository.remove(bookId, customerId);

        Assert.assertEquals(null, bookRepository.get(bookId, customerId));
    }


    @Test
    public void getAll() {
        int customerId = TestData.FIRST_CUSTOMER.getId();

        List<Book> all = bookRepository.getAll(customerId);

        assertThat(TestData.books)
                .usingRecursiveComparison()
                .ignoringFields("details", "customer", "notes")
                .isEqualTo(all);
    }


}