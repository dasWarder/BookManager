package com.babichev.bookmanager.entity;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * A role entity to interact with the role objects
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "roles")
public class Role extends AbstractIdEntity {

    @NotNull
    @NotBlank
    private String name;

    public Role(String name) {
        this.name = name;
    }

    public Role(Integer id, String name) {
        super(id);
        this.name = name;
    }
}
