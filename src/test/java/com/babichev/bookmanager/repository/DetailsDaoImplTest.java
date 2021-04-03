package com.babichev.bookmanager.repository;

import com.babichev.bookmanager.data.TestData;
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
        Details details = detailsDao.get(5, 1);
        Details firstDetails = TestData.FIRST_DETAILS;
        firstDetails.setBook(details.getBook());

        assertThat(firstDetails).usingRecursiveComparison().isEqualTo(details);
    }

}