package com.babichev.bookmanager.service;

import com.babichev.bookmanager.entity.Customer;

public interface CustomerService {

    Customer add(Customer customer);

    Customer get(int customer_id);

    void remove(int customer_id);
}
