package com.babichev.bookmanager.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "roles")
public class Role extends AbstractIdEntity {

    private String name;

    public Role(String name) {
        this.name = name;
    }

    public Role(Integer id, String name) {
        super(id);
        this.name = name;
    }
}
