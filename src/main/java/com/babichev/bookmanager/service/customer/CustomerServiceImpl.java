package com.babichev.bookmanager.service.customer;


import com.babichev.bookmanager.entity.Customer;
import com.babichev.bookmanager.repository.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public Customer add(Customer customer) {
        Assert.notNull(customer, "customer must not be null");
        return customerRepository.add(customer);
    }

    @Override
    @Transactional
    public Customer get(int customer_id) {
        return customerRepository.get(customer_id);
    }

    @Override
    @Transactional
    public void remove(int customer_id) {
        customerRepository.remove(customer_id);
    }

}
