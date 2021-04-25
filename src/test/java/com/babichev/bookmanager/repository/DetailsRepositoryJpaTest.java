package com.babichev.bookmanager.repository;

import com.babichev.bookmanager.data.TestData;
import com.babichev.bookmanager.entity.Details;
import com.babichev.bookmanager.repository.detail.DetailsRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static com.babichev.bookmanager.data.TestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = {"/db/clearDb.sql", "/db/populateDb.sql"})
public class DetailsRepositoryJpaTest {

    @Autowired
    private DetailsRepository detailsRepository;

    @Test
    public void add() {
        Details details = createDetails();
        Details add = detailsRepository.add(details, FIRST_BOOK.getId());
        details.setId(add.getId());

        Assert.assertEquals(details, add);
    }

    @Test
    public void update() {
        Details added = detailsRepository.get(FIRST_DETAILS.getId(), FIRST_BOOK.getId());
        int id = added.getId();
        Details forUpdate = TestData.updatedDetails(added);
        forUpdate = detailsRepository.add(forUpdate, FIRST_BOOK.getId());

        assertThat(forUpdate)
                .usingRecursiveComparison()
                .ignoringFields("book")
                .isEqualTo(detailsRepository.get(id, FIRST_BOOK.getId()));
    }

    @Test
    public void getById() {
        Details details = detailsRepository.get(FIRST_DETAILS.getId(), FIRST_BOOK.getId());
        Details firstDetails = FIRST_DETAILS;
        firstDetails.setBook(details.getBook());

        assertThat(firstDetails)
                .usingRecursiveComparison()
                .ignoringFields("book")
                .isEqualTo(details);
    }

    @Test
    public void remove() {
        Details details = FIRST_DETAILS;
        Details add = detailsRepository.add(FIRST_DETAILS, FIRST_BOOK.getId());
        Details getted = detailsRepository.get(FIRST_DETAILS.getId(), FIRST_BOOK.getId());

        assertThat(add)
                .usingRecursiveComparison()
                .ignoringFields("book")
                .isEqualTo(getted);

        detailsRepository.remove(details.getId(), FIRST_BOOK.getId());

        Assert.assertEquals(null, detailsRepository.get(details.getId(), FIRST_BOOK.getId()));
    }

}