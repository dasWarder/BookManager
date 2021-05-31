package com.babichev.bookmanager.repository.customer;

import com.babichev.bookmanager.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;


/**
 * An interface describes the layer between the service and the database for the customer entity
 */
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Customer getCustomerById(int customerId);

    @Transactional
    void deleteCustomerById(int customerId);

    Optional<Customer> getCustomerByLogin(String login);
}
