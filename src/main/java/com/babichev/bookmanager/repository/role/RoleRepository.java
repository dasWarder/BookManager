package com.babichev.bookmanager.repository.role;

import com.babichev.bookmanager.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * An interface describes the layer between the service and the database for the role entity
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    /**
     * The method to receive the role by its id
     * @param roleId the id of the role
     * @return the role with @param roleId
     */
    Role getRoleById(int roleId);

    /**
     * The method to remove a role by its id
     * @param roleId the id of the role
     */
    @Transactional
    void deleteRoleById(int roleId);

    /**
     * The method to get a role by @param name
     * @param name the name which will be use for searching
     * @return the optional with NULL or the role object
     */
    Optional<Role> getRoleByName(String name);
}
