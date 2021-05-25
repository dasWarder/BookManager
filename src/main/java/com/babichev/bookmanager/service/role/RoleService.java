package com.babichev.bookmanager.service.role;

import com.babichev.bookmanager.entity.Role;

/**
 * An interface that describes a behavior of a class on the service layer (between the controller and the repository)
 */
public interface RoleService {

    /**
     * The method to validate a role object for storage it to the database
     * @param role a role object that must be validated
     * @return the role object with ID stored to the database
     */
    Role add(Role role);

    /**
     * The method to validate a role object for receiving it from the database
     * @param roleId the ID of a role
     * @return the role object from the database
     */
    Role get(int roleId);

    /**
     * The method to validate a role object for removing from the database
     * @param roleId the ID of a role
     */
    void remove(int roleId);

    /**
     * The method to validate a role object for receiving it by a name
     * @param name the name of a role that must be received from the database
     * @return the role object from the database
     */
    Role getByName(String name);
}
