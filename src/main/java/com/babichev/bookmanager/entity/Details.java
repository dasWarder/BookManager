package com.babichev.bookmanager.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@NoArgsConstructor
@Table(name = "detail")
@EqualsAndHashCode(exclude = "book")
public class Details extends AbstractIdEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;


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
