package com.babichev.bookmanager.repository.role;

import com.babichev.bookmanager.entity.Role;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * An interface describes the layer between the service and the database for the role entity
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role getRoleById(int roleId);

    @Transactional
    void deleteRoleById(int roleId);

    Optional<Role> getRoleByName(String name);
}
