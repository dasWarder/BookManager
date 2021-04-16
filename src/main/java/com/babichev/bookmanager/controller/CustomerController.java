package com.babichev.bookmanager.controller;


import com.babichev.bookmanager.entity.Customer;
import com.babichev.bookmanager.service.customer.CustomerService;
import com.babichev.bookmanager.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/customer")
    public String showLogIn() {
        return "loginForm";
    }


    @GetMapping(value = "/customer/login")
    public String logIn(@RequestParam("customer") int id) {
        Customer customer = customerService.get(id);

        if (customer != null) {
            SecurityUtil.setAuthUser(customer.getId());
        }

        return "redirect:/books";
    }
}
