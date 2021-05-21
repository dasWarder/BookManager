package com.babichev.bookmanager.util.mapping;

import com.babichev.bookmanager.dto.CustomerDTO;
import com.babichev.bookmanager.entity.Customer;
import com.babichev.bookmanager.entity.Role;
import com.babichev.bookmanager.repository.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class CustomerMapper {

    private RoleRepository roleRepository;

    @Autowired
    public CustomerMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Customer fromDtoToUserCustomer(CustomerDTO dto) {
        Set<Role> roles = new HashSet<>();
        roles.add(getRole("USER"));

        Customer customer = new Customer()
                .builder()
                .login(dto.getLogin())
                .password(dto.getPassword())
                .roles(roles)
                .books(new ArrayList())
                .enabled(true).build();

        return customer;
    }

    private Role getRole(String name) {
        Optional<Role> byName = roleRepository.getByName(name);

        if(!byName.isPresent()) {
            Role role = new Role(name);
            roleRepository.add(role);

            return role;
        }

        return byName.get();
    }
}
