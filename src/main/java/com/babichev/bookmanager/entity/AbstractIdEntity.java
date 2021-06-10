package com.babichev.bookmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * A parent entity with ID
 */
@Data
@MappedSuperclass
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Access(value = AccessType.FIELD)
public class AbstractIdEntity {

    @Id
    @SequenceGenerator(name = "book_id_seq", sequenceName = "book_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_seq")
    private Integer id;

}
