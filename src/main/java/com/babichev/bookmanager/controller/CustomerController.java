package com.babichev.bookmanager.controller;

import com.babichev.bookmanager.dto.CustomerDTO;
import com.babichev.bookmanager.entity.Customer;
import com.babichev.bookmanager.service.customer.CustomerService;
import com.babichev.bookmanager.util.mapping.CustomerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class CustomerController {

    private CustomerService customerService;
    private CustomerMapper mapper;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerController(CustomerService customerService, CustomerMapper mapper, PasswordEncoder passwordEncoder) {
        this.customerService = customerService;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "/register")
    public String signUp(Model model) {

        model.addAttribute("customer", new CustomerDTO());

        return "signUp";
    }

    @PostMapping(value = "/register")
    public String register(CustomerDTO dto) {
        String encoded = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(encoded);

        Customer customer = mapper.fromDtoToUserCustomer(dto);
        customerService.add(customer);

        return "login";
    }

}
