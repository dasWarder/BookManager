package com.babichev.bookmanager.repository;

import com.babichev.bookmanager.data.TestData;
import com.babichev.bookmanager.entity.Book;
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
        Book bookById = bookRepository.get(TestData.FIRST_BOOK.getId());
        assertThat(bookById).usingRecursiveComparison()
                .ignoringFields("details", "customer")
                .isEqualTo(TestData.FIRST_BOOK);
    }

    @Test
    public void getByWrongId() {
        Book bookById = bookRepository.get(TestData.WRONG_ID_BOOK.getId());
        System.out.println(bookRepository.get(TestData.FIRST_BOOK.getId()));
        Assert.assertEquals(null, bookById);
    }

    @Test
    public void add() {
        Book saved = bookRepository.add(TestData.createBook());
        int id = saved.getId();
        Book mockBookWithId = TestData.createBook();
        mockBookWithId.setId(id);

        assertThat(bookRepository.get(id))
                .usingRecursiveComparison()
                .ignoringFields("details", "customer")
                .isEqualTo(mockBookWithId);
    }

    @Test
    public void update() {
        Book added = bookRepository.get(TestData.FIRST_BOOK.getId());
        int id = added.getId();
        Book forUpdate = TestData.updatedBook(added);
        forUpdate = bookRepository.add(forUpdate);

        assertThat(forUpdate)
                .usingRecursiveComparison()
                .ignoringFields("details", "customer")
                .isEqualTo(bookRepository.get(id));
    }


    @Test
    public void remove() {
        Book getted = bookRepository.get(TestData.FIRST_BOOK.getId());

        assertThat(getted)
                .usingRecursiveComparison()
                .ignoringFields("details", "customer")
                .isEqualTo(TestData.FIRST_BOOK);

        bookRepository.remove(getted.getId());

        Assert.assertEquals(null, bookRepository.get(getted.getId()));
    }


    @Test
    public void getAll() {
        List<Book> all = bookRepository.getAll();

        assertThat(TestData.books)
                .usingRecursiveComparison()
                .ignoringFields("details", "customer")
                .isEqualTo(all);
    }


}