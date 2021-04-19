package com.babichev.bookmanager.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;


@Data
@ToString
@Entity
@Table(name = "book")
public class Book extends AbstractIdEntity{

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "year")
    private Integer year;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(mappedBy = "book", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.LAZY, orphanRemoval = true)
    private Details details;

    @OneToMany(mappedBy = "book", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH}, orphanRemoval = true, fetch = FetchType.LAZY)
    private Collection<Note> notes;

    public Book() {};

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
