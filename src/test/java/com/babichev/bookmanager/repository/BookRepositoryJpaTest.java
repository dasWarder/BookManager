package com.babichev.bookmanager.repository;

import com.babichev.bookmanager.data.TestData;
import com.babichev.bookmanager.entity.Book;
import com.babichev.bookmanager.repository.book.BookRepository;
import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        Book bookById = bookRepository.getBookByIdAndCustomer_Id(TestData.FIRST_BOOK.getId(), customerId);

        assertThat(bookById).usingRecursiveComparison()
                .ignoringFields("details", "customer", "notes")
                .isEqualTo(TestData.FIRST_BOOK);
    }

    @Test
    public void getByWrongId() {
        int customerId = TestData.FIRST_CUSTOMER.getId();

        Book bookById = bookRepository.getBookByIdAndCustomer_Id(TestData.WRONG_ID_BOOK.getId(), customerId);

        Assert.assertEquals(null, bookById);
    }

    @Test
    public void add() {
        int customerId = TestData.FIRST_CUSTOMER.getId();
        Book toSave = TestData.createBook();
        toSave.setCustomer(TestData.FIRST_CUSTOMER);
        Book saved = bookRepository.save(toSave);
        int id = saved.getId();
        Book mockBookWithId = TestData.createBook();
        mockBookWithId.setId(id);

        Book bookByIdAndCustomer_id = bookRepository.getBookByIdAndCustomer_Id(id, customerId);

        assertThat(bookByIdAndCustomer_id)
                .usingRecursiveComparison()
                .ignoringFields("details", "customer", "notes")
                .isEqualTo(mockBookWithId);
    }

    @Test
    public void update() {
        int customerId = TestData.FIRST_CUSTOMER.getId();

        Book added = bookRepository.getBookByIdAndCustomer_Id(TestData.FIRST_BOOK.getId(), customerId);
        int id = added.getId();
        Book forUpdate = TestData.updatedBook(added);
        forUpdate = bookRepository.save(forUpdate);

        assertThat(forUpdate)
                .usingRecursiveComparison()
                .ignoringFields("details", "customer")
                .isEqualTo(bookRepository.getBookByIdAndCustomer_Id(id, customerId));
    }


    @Test
    public void remove() {
        int bookId = TestData.FOURTH_BOOK.getId();
        int customerId = TestData.FIRST_CUSTOMER.getId();

        bookRepository.deleteBookByIdAndCustomer_Id(bookId, customerId);

        Assert.assertEquals(null, bookRepository.getBookByIdAndCustomer_Id(bookId, customerId));
    }


    @Test
    public void getAll() {
        Pageable pageable = Pageable.unpaged();
        int customerId = TestData.FIRST_CUSTOMER.getId();

        Page<Book> all = bookRepository.getBooksByCustomer_Id(customerId, pageable);

        assertThat(TestData.books)
                .usingRecursiveComparison()
                .ignoringFields("details", "customer", "notes")
                .isEqualTo(all);
    }


}