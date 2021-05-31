package com.babichev.bookmanager.repository;


import com.babichev.bookmanager.entity.Customer;
import com.babichev.bookmanager.repository.customer.CustomerRepository;
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
public class CustomerRepositoryJpaTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void add() {
        Customer customer = createCustomer();
        Customer add = customerRepository.save(customer);
        customer.setId(add.getId());

        assertThat(add)
                .usingRecursiveComparison()
                .ignoringFields("books", "roles")
                .isEqualTo(customer);
    }

    @Test
    public void getById() {
        Customer customer = customerRepository.getCustomerById(FIRST_CUSTOMER.getId());
        Customer firstCustomer = FIRST_CUSTOMER;
        firstCustomer.setBooks(customer.getBooks());

        assertThat(firstCustomer)
                .usingRecursiveComparison()
                .ignoringFields("books", "roles")
                .isEqualTo(customer);
    }

    @Test
    public void remove() {
        Customer getted = customerRepository.getCustomerById(SECOND_CUSTOMER.getId());

        assertThat(getted)
                .usingRecursiveComparison()
                .ignoringFields("books", "roles")
                .isEqualTo(SECOND_CUSTOMER);

        customerRepository.deleteCustomerById(getted.getId());

        Assert.assertEquals(null, customerRepository.getCustomerById(getted.getId()));
    }

    @Test
    public void update() {
        Customer getted = customerRepository.getCustomerById(FIRST_CUSTOMER.getId());
        int customerId = getted.getId();

        Customer updated = updatedCustomer(getted);
        Customer addUpdated = customerRepository.save(updated);

        assertThat(addUpdated)
                .usingRecursiveComparison()
                .ignoringFields("books", "roles")
                .isEqualTo(customerRepository.getCustomerById(customerId));
    }
}