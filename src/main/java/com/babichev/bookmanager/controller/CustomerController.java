package com.babichev.bookmanager.controller;


import com.babichev.bookmanager.entity.Customer;
import com.babichev.bookmanager.service.customer.CustomerService;
import com.babichev.bookmanager.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.isNull;

@Controller
@Slf4j
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/customer")
    public String showLogIn() {
        log.info("Move to login page");

        return "loginForm";
    }


    @GetMapping(value = "/customer/login")
    public String logIn(@RequestParam("customer") int id) {
        log.info("Select customer with id {}", id);
        Customer customer = customerService.get(id);

        if (!isNull(customer)) {
            SecurityUtil.setAuthUser(customer.getId());
        }

        return "redirect:/books";
    }
}
