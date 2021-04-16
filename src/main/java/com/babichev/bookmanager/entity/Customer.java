package com.babichev.bookmanager.entity;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;


@Data
@ToString
@Entity
@Table(name = "customer")
public class Customer extends AbstractIdEntity {

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH}, orphanRemoval = true, fetch = FetchType.LAZY)
    private Collection<Book> books;

    public Customer() {}

    public Customer(Integer id, String login, String password) {
        super(id);
        this.login = login;
        this.password = password;
    }

    public Customer(String login, String password) {
        this.login = login;
        this.password = password;
    }

}
