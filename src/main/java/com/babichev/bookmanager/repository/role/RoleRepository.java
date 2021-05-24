package com.babichev.bookmanager.repository.role;

import com.babichev.bookmanager.entity.Role;
import java.util.Optional;

/**
 * An interface describes the layer between the service and the database for the role entity
 */
public interface RoleRepository {

    /**
     * The method to store a new role to the database
     * @param role an entity of the role, that must be stored to the database
     * @return a role object with id, stored to the database
     */
    Role add(Role role);

    /**
     * The method to get a role from the database by its ID
     * @param roleId the ID of a role, that must be gotten form the database
     * @return the role from the database
     */
    Role get(int roleId);

    /**
     * The method to remove a role from the database by its ID
     * @param roleId the ID of a role, that must be removed
     */
    void remove(int roleId);

    /**
     * The method to get a role by a name from the database
     * @param name the name of a possible role
     * @return an optional object of a role form the database
     */
    Optional<Role> getByName(String name);
}
