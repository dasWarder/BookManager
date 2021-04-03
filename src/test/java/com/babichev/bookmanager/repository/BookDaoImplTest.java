package com.babichev.bookmanager.repository;

import com.babichev.bookmanager.data.TestData;
import com.babichev.bookmanager.entity.Book;
import com.babichev.bookmanager.service.BookService;
import com.babichev.bookmanager.service.BookServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = {"/db/clearDb.sql", "/db/populateDb.sql"})
public class BookDaoImplTest {

    @Autowired
    private BookDao bookDao;


    @Test
    public void getBookById() {
        Book bookById = bookDao.getBookById(1);
        assertThat(bookById).usingRecursiveComparison().isEqualTo(TestData.FIRST_BOOK);
    }

    @Test
    public void getByWrongId() {
        Book bookById = bookDao.getBookById(TestData.WRONG_ID_BOOK.getId());
        System.out.println(bookDao.getBookById(TestData.FIRST_BOOK.getId()));
        Assert.assertEquals(null, bookById);
    }

    @Test
    @Sql(scripts = "/db/createDb.sql")
    public void saveBook() {
        Book saved = bookDao.addBook(TestData.createNew());
        int id = saved.getId();
        Book mockBookWithId = TestData.createNew();
        mockBookWithId.setId(id);

        assertThat(bookDao.getBookById(id))
                .usingRecursiveComparison()
                .isEqualTo(mockBookWithId);
    }

    @Test
    public void updateBook() {
        Book added = bookDao.getBookById(TestData.FIRST_BOOK.getId());
        int id = added.getId();
        Book forUpdate = TestData.updated(added);
        forUpdate = bookDao.addBook(forUpdate);

        assertThat(forUpdate)
                .usingRecursiveComparison()
                .isEqualTo(bookDao.getBookById(id));
    }


    @Test
    public void removeBook() {
        Book getted = bookDao.getBookById(TestData.FIRST_BOOK.getId());

        assertThat(getted)
                .usingRecursiveComparison()
                .isEqualTo(TestData.FIRST_BOOK);

        bookDao.removeBook(getted.getId());

        Assert.assertEquals(null, bookDao.getBookById(getted.getId()));
    }


    @Test
    public void getAllBook() {
        List<Book> all = bookDao.getAll();

        assertThat(TestData.books)
                .usingRecursiveComparison()
                .isEqualTo(all);
    }


}