package com.babichev.bookmanager.repository.role;

import com.babichev.bookmanager.entity.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

/**
 * An implementation of RoleRepository interface
 */
@Repository
public class RoleRepositoryJpa implements RoleRepository {

    /**
     * An object of entity manager to interact with the database
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * The method that implements to save command
     * @param role an entity of the role, that must be stored to the database
     * @return the object of a role stored to database
     */
    @Override
    @Transactional
    public Role add(Role role) {
        if(isNull(role.getId())) {
            /**
             * @see EntityManager#persist(Object) 
             */
            em.persist(role);
            return role;
        } else if (isNull(get(role.getId()))) {
            return null;
        }

        /**
         * @see EntityManager#merge(Object) 
         */
        return em.merge(role);
    }

    /**
     * The method that implements to get command
     * @param roleId the ID of a role, that must be gotten form the database
     * @return the object of a role from the database
     */
    @Override
    @Transactional
    public Role get(int roleId) {

        /**
         * @see EntityManager#find(Class, Object) 
         */
        return em.find(Role.class, roleId);
    }

    /**
     * The method that implements to remove command
     * @param roleId the ID of a role, that must be removed
     */
    @Override
    @Transactional
    public void remove(int roleId) {
        Role role = get(roleId);

        /**
         * @see EntityManager#remove(Object) 
         */
        em.remove(role);
    }

    /**
     * The method that implements to getByName command
     * @param name the name of a possible role
     * @return an optional object with a role or null if the role doesn't exist
     */
    @Override
    @Transactional
    public Optional<Role> getByName(String name) {
        /**
         * @see EntityManager#createQuery(String) 
         */
        Query get_role_from_r_by_name = em.createQuery("SELECT r FROM Role r WHERE r.name=:name")
                .setParameter("name", name);

        List resultList = get_role_from_r_by_name.getResultList();

        return resultList.stream()
                .findFirst();
    }
}
