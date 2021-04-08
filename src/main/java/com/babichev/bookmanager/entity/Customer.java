package com.babichev.bookmanager.entity;


import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "customer")
public class Customer extends AbstractIdEntity {

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Book> getBooks() {
        return books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }
}
