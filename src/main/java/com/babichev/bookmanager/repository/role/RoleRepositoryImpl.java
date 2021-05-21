package com.babichev.bookmanager.repository.role;

import com.babichev.bookmanager.entity.Book;
import com.babichev.bookmanager.entity.Customer;
import com.babichev.bookmanager.entity.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;


@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Role add(Role role) {
        if(isNull(role.getId())) {
            em.persist(role);
            return role;
        } else if (isNull(get(role.getId()))) {
            return null;
        }

        return em.merge(role);
    }

    @Override
    @Transactional
    public Role get(int roleId) {
        return em.find(Role.class, roleId);
    }

    @Override
    @Transactional
    public void remove(int roleId) {
        Role role = get(roleId);

        em.remove(role);
    }

    @Override
    @Transactional
    public Optional<Role> getByName(String name) {
        Query get_role_from_r_by_name = em.createQuery("SELECT r FROM Role r WHERE r.name=:name")
                .setParameter("name", name);

        List resultList = get_role_from_r_by_name.getResultList();

        return resultList.stream()
                .findFirst();
    }
}
