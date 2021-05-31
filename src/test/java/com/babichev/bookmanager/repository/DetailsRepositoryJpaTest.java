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
        details.setBook(FIRST_BOOK);
        Details add = detailsRepository.save(details);
        details.setId(add.getId());

        Assert.assertEquals(details, add);
    }

    @Test
    public void update() {
        Details added = detailsRepository.getByIdAndBook_Id(FIRST_DETAILS.getId(), FIRST_BOOK.getId());
        int id = added.getId();
        Details forUpdate = TestData.updatedDetails(added);
        forUpdate = detailsRepository.save(forUpdate);

        assertThat(forUpdate)
                .usingRecursiveComparison()
                .ignoringFields("book")
                .isEqualTo(detailsRepository.getByIdAndBook_Id(id, FIRST_BOOK.getId()));
    }

    @Test
    public void getById() {
        Details details = detailsRepository.getByIdAndBook_Id(FIRST_DETAILS.getId(), FIRST_BOOK.getId());
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
        details.setBook(FIRST_BOOK);
        Details add = detailsRepository.save(FIRST_DETAILS);
        Details getted = detailsRepository.getByIdAndBook_Id(FIRST_DETAILS.getId(), FIRST_BOOK.getId());

        assertThat(add)
                .usingRecursiveComparison()
                .ignoringFields("book")
                .isEqualTo(getted);

        detailsRepository.deleteByIdAndBook_Id(details.getId(), FIRST_BOOK.getId());

        Assert.assertEquals(null, detailsRepository.getByIdAndBook_Id(details.getId(), FIRST_BOOK.getId()));
    }

}