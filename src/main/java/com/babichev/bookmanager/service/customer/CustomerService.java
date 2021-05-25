package com.babichev.bookmanager.service.customer;

import com.babichev.bookmanager.entity.Customer;


/**
 * An interface that describes a behavior of a class on the service layer (between the controller and the repository)
 */
public interface CustomerService {

    /**
     * The method to validate a customer object for storage it to the database
     * @param customer a customer object that must be validated
     * @return the customer object with ID stored to the database
     */
    Customer add(Customer customer);

    /**
     * The method to validate a customer object for receiving it from the database
     * @param customerId the ID of a customer
     * @return the book object from the database
     */
    Customer get(int customerId);

    /**
     * The method to validate a book object for removing from the database
     * @param customerId the ID of a customer
     */
    void remove(int customerId);


    /**
     * The method to validate a customer object for receiving it by a name
     * @param name the name of a customer that must be received from the database
     * @return the customer object from the database
     */
    Customer getByUsername(String name);
}
