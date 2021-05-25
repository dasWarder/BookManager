package com.babichev.bookmanager.service.role;

import com.babichev.bookmanager.entity.Role;
import com.babichev.bookmanager.exception.RoleNotFoundException;
import com.babichev.bookmanager.repository.customer.CustomerRepository;
import com.babichev.bookmanager.repository.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * The service class that implements RoleService interface
 */
@Service
public class RoleServiceImpl implements RoleService {

    /**
     * The field with a customer repository bean
     * @see RoleRepository
     */
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * The method to validate the role for to save command
     * @param role a role object that must be validated
     * @return the role object with ID stored to the database on the repository layer
     */
    @Override
    public Role add(Role role) {
        Assert.notNull(role, "Role couldn't be null");

        /**
         * @see RoleRepository#add(Role) 
         */
        Role stored = roleRepository.add(role);

        return stored;
    }

    /**
     * The method to validate the role object for to get command
     * @param roleId the ID of a role
     * @return the role object from the database
     */
    @Override
    public Role get(int roleId) {

        /**
         * @see RoleRepository#get(int) 
         */
        Role role = roleRepository.get(roleId);

        if (role == null) {
            throw new RoleNotFoundException(
                    String.format("Role with id=%d doesn't exist!", roleId));
        }

        return role;
    }

    /**
     * The method to validate the role object for to remove command
     * @param roleId the ID of a role
     */
    @Override
    public void remove(int roleId) {
        /**
         * @see RoleRepository#remove(int) 
         */
        roleRepository.remove(roleId);
    }

    /**
     * The method to validate the role object for to getByName command
     * @param name the name of a role that must be received from the database
     * @return the role object from the repository layer
     */
    @Override
    public Role getByName(String name) {

        /**
         * @see RoleRepository#getByName(String) 
         */
        Optional<Role> byName = roleRepository.getByName(name);

        if(!byName.isPresent()) {
            throw new RoleNotFoundException(
                    String.format("Role with name=%s not found!", name));
        }

        Role role = byName.get();

        return role;
    }
}
