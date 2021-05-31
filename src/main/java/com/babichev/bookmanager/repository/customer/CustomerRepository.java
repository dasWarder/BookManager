package com.babichev.bookmanager.repository.customer;

import com.babichev.bookmanager.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


/**
 * An interface describes the layer between the service and the database for the customer entity
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    /**
     * The method to get a customer by its id
     * @param customerId the id of a customer
     * @return the customer received from the database
     */
    Customer getCustomerById(int customerId);

    /**
     * The method to remove the customer from database by its id
     * @param customerId the customer id that must be removed
     */
    @Transactional
    void deleteCustomerById(int customerId);

    /**
     * The method to get the customer by its login
     * @param login the login of the customer
     * @return the optional object with Null or the customer object
     */
    Optional<Customer> getCustomerByLogin(String login);
}
