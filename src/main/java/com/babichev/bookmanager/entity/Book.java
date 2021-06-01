package com.babichev.bookmanager.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotNull
    @NotBlank(message = "The book title must be non-empty")
    @Size(min = 1, max = 300, message = "The length of the title must be less that 300 symbols")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @NotBlank(message = "The author field must be non-empty")
    @Size(min = 1, max = 300, message = "The length of the author field must be less that 300 symbols")
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
