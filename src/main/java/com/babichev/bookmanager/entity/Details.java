package com.babichev.bookmanager.entity;


import lombok.Data;

import javax.persistence.*;



@Entity
@Table(name = "detail")
public class Details extends AbstractIdEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
