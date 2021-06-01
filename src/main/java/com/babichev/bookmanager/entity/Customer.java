package com.babichev.bookmanager.entity;


import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

/**
 * A customer entity to interact with the customer objects
 */
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@Table(name = "users")
public class Customer extends AbstractIdEntity {

    @Email
    @NotNull
    @NotBlank
    @Size(min = 8, max = 255)
    @Column(name = "username", nullable = false, length = 255)
    private String login;

    @NotNull
    @NotBlank
    @Size(min = 7, max = 60)
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH},
            orphanRemoval = true, fetch = FetchType.LAZY)
    private Collection<Book> books;

    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id")
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

    public Customer(Integer id, String login, String password, boolean enabled) {
        super(id);
        this.login = login;
        this.password = password;
        this.enabled = enabled;
    }
}
