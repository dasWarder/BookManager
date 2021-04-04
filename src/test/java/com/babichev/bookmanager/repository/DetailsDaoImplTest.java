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

import static com.babichev.bookmanager.data.TestData.*;
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
        Details details = createNewDetails();
        Details add = detailsDao.add(details, FIRST_BOOK.getId());
        details.setId(add.getId());

        Assert.assertEquals(details, add);
    }

    @Test
    public void getById() {
        Details details = detailsDao.get(FIRST_DETAILS.getId(), FIRST_BOOK.getId());
        Details firstDetails = FIRST_DETAILS;
        firstDetails.setBook(details.getBook());

        assertThat(firstDetails).usingRecursiveComparison().isEqualTo(details);
    }

    @Test
    public void remove() {
        Details details = FIRST_DETAILS;
        Details add = detailsDao.add(FIRST_DETAILS, FIRST_BOOK.getId());
        Details getted = detailsDao.get(FIRST_DETAILS.getId(), FIRST_BOOK.getId());

        assertThat(add)
                .usingRecursiveComparison()
                .isEqualTo(getted);

        detailsDao.remove(details.getId(), FIRST_BOOK.getId());

        Assert.assertEquals(null, detailsDao.get(details.getId(), FIRST_BOOK.getId()));
    }

}