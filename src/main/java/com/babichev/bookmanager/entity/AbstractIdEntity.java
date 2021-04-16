package com.babichev.bookmanager.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode
@MappedSuperclass
@Access(value = AccessType.FIELD)
public class AbstractIdEntity {

    @Id
    @SequenceGenerator(name = "book_id_seq", sequenceName = "book_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_seq")
    private Integer id;

    public AbstractIdEntity(Integer id) {
        this.id = id;
    }

    public AbstractIdEntity() {}

}
