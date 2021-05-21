package com.babichev.bookmanager.security;

import com.babichev.bookmanager.entity.Customer;
import com.babichev.bookmanager.repository.customer.CustomerRepository;
import com.babichev.bookmanager.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private CustomerService customerService;

    @Autowired
    public UserDetailsServiceImpl(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Customer customer = customerService.getByUsername(name);


        return new MyUserDetails(customer);
    }
}
