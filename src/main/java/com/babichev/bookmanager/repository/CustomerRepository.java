package com.babichev.bookmanager.repository;

import com.babichev.bookmanager.entity.Customer;

public interface CustomerRepository {

    Customer add(Customer customer);

    Customer get(int customer_id);

    void remove(int customer_id);
}
