package com.babichev.bookmanager.repository.customer;

import com.babichev.bookmanager.entity.Customer;

import java.util.Optional;


/**
 * An interface describes the layer between the service and the database for the customer entity
 */
public interface CustomerRepository {

    /**
     * The method to store a new customer to the database
     * @param customer an entity of the customer, that must be stored to the database
     * @return a customer object with id, stored to the database
     */
    Customer add(Customer customer);

    /**
     * The method to get a customer from the database by its ID
     * @param customerId the ID of the customer, that must be gotten from the database
     * @return the customer from the database
     */
    Customer get(int customerId);

    /**
     * The method to remove a customer from the database by its ID
     * @param customerId the ID of a customer, that must be removed
     */
    void remove(int customerId);

    /**
     * The method to get a customer from the database by its login
     * @param name the name of a possible customer
     * @return the optional object of the customer or null
     */
    Optional<Customer> getCustomerByUsername(String name);
}
