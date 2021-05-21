package com.babichev.bookmanager.repository.customer;

import com.babichev.bookmanager.entity.Customer;

import java.util.Optional;

public interface CustomerRepository {

    Customer add(Customer customer);

    Customer get(int customer_id);

    void remove(int customer_id);

    Optional<Customer> getCustomerByUsername(String name);
}
