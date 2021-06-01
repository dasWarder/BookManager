package com.babichev.bookmanager.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


/**
 * A details entity to interact with the details objects
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "note")
@EqualsAndHashCode(exclude = "book")
public class Note extends AbstractIdEntity {

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateAndTime;

    @Column(name = "text", nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "book_id")
    private Book book;

    public Note(Integer id, LocalDateTime dateAndTime, String text) {
        super(id);
        this.dateAndTime = dateAndTime;
        this.text = text;
    }

    public Note(LocalDateTime dateAndTime, String text) {
        this.dateAndTime = dateAndTime;
        this.text = text;
    }

}
