package com.babichev.bookmanager.entity;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;



@Data
@ToString
@Entity
@Table(name = "detail")
public class Details extends AbstractIdEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "customer_comment")
    private String comment;


    public Details() {};


    public Details(String description, String image) {
        this.description = description;
        this.image = image;
    }

    public Details(Integer id, String description, String image) {
        super(id);
        this.description = description;
        this.image = image;
    }

}
