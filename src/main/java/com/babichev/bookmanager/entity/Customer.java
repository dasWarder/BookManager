package com.babichev.bookmanager.entity;


import lombok.*;


import javax.persistence.*;
import java.util.*;


@Data
@Entity
@Builder
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "users")
public class Customer extends AbstractIdEntity {


    @Column(name = "username")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH},
            orphanRemoval = true, fetch = FetchType.LAZY)
    private Collection<Book> books;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;


    public Customer(Integer id, String login, String password) {
        super(id);
        this.login = login;
        this.password = password;
    }

    public Customer(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Customer(String login, String password, boolean enabled) {
        this.login = login;
        this.password = password;
        this.enabled = enabled;
    }

    public Customer(String login, String password, boolean enabled, Set<Role> roles) {
        this.login = login;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

    public Customer(String login, String password, boolean enabled, Collection<Book> books, Set<Role> roles) {
        this.login = login;
        this.password = password;
        this.enabled = enabled;
        this.books = books;
        this.roles = roles;
    }
}
