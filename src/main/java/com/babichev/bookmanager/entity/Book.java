package com.babichev.bookmanager.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;


/**
 * A book entity to interact with the book objects
 */
@Getter
@Setter
@Entity
@Table(name = "book")
@NoArgsConstructor
@EqualsAndHashCode(exclude = "customer")
public class Book extends AbstractIdEntity{

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "year",nullable = true)
    private Integer year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(mappedBy = "book", fetch = FetchType.LAZY , orphanRemoval = true)
    private Details details;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private Collection<Note> notes;


    public Book(String name, String author, Integer year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book(Integer id, String name, String author, Integer year) {
        super(id);
        this.name = name;
        this.author = author;
        this.year = year;
    }
}
