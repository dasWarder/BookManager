package com.babichev.bookmanager.entity;

import javax.persistence.*;
import java.util.Objects;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractIdEntity that = (AbstractIdEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
