package com.babichev.bookmanager.service.customer;


import com.babichev.bookmanager.entity.Customer;
import com.babichev.bookmanager.repository.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer add(Customer customer) {
        return customerRepository.add(customer);
    }

    @Override
    public Customer get(int customer_id) {
        return customerRepository.get(customer_id);
    }

    @Override
    public void remove(int customer_id) {
        customerRepository.remove(customer_id);
    }

}
