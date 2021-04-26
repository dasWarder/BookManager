package com.babichev.bookmanager.repository.customer;

import com.babichev.bookmanager.entity.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static java.util.Objects.isNull;

@Repository
public class CustomerRepositoryJpa implements CustomerRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Customer add(Customer customer) {
        if(isNull(customer.getId())) {
            em.persist(customer);
            return customer;
        } else if (isNull(get(customer.getId()))) {
            return null;
        }

        return em.merge(customer);
    }

    @Override
    @Transactional
    public Customer get(int customer_id) {
        return em.find(Customer.class, customer_id);
    }

    @Override
    @Transactional
    public void remove(int customer_id) {
        Customer customer = get(customer_id);

        em.remove(customer);
    }
}
