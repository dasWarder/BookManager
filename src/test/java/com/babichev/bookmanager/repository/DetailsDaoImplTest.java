package com.babichev.bookmanager.repository;

import com.babichev.bookmanager.data.TestData;
import com.babichev.bookmanager.entity.Book;
import com.babichev.bookmanager.entity.Details;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = {"/db/clearDb.sql", "/db/populateDb.sql"})
public class DetailsDaoImplTest {

    @Autowired
    private DetailsDao detailsDao;

    @Test
    public void addNew() {
        Details details = new Details("TEST", "TEST");
        Details add = detailsDao.add(details, 1);
        details.setId(add.getId());

        Assert.assertEquals(details, add);
    }

    @Test
    public void getById() {
        Details details = detailsDao.get(TestData.FIRST_DETAILS.getId(), 1);
        Details firstDetails = TestData.FIRST_DETAILS;
        firstDetails.setBook(details.getBook());

        assertThat(firstDetails).usingRecursiveComparison().isEqualTo(details);
    }

    @Test
    public void remove() {
        Details details = TestData.FIRST_DETAILS;
        Details add = detailsDao.add(details, 3);
        Details getted = detailsDao.get(TestData.FIRST_DETAILS.getId(), TestData.THIRD_BOOK.getId());

        assertThat(getted)
                .usingRecursiveComparison()
                .isEqualTo(add);

        detailsDao.remove(details.getId(), TestData.THIRD_BOOK.getId());

        Assert.assertEquals(null, detailsDao.get(details.getId(), TestData.THIRD_BOOK.getId()));
    }

}