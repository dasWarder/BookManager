package com.babichev.bookmanager.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "roles")
public class Role extends AbstractIdEntity {

    private String name;

    public Role(String name) {
        this.name = name;
    }
}
