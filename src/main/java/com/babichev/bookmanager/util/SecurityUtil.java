package com.babichev.bookmanager.util;

import com.babichev.bookmanager.entity.Customer;
import com.babichev.bookmanager.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

    @Autowired
    private CustomerService customerService;

    public SecurityUtil() {
    }

    public Integer getAuthUserId() {
        return customerFromDb().getId();
    }

    private Customer customerFromDb() {
        Customer customer = customerService.getByUsername(getLoggedUser());

        return customer;
    }

    private String getLoggedUser() {
        UserDetails customer = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return customer.getUsername();
    }
}
