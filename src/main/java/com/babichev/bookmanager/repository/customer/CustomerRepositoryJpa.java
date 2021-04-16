package com.babichev.bookmanager.repository.customer;

import com.babichev.bookmanager.entity.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CustomerRepositoryJpa implements CustomerRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Customer add(Customer customer) {
        if(customer.getId() == null) {
            em.persist(customer);
            return customer;
        } else if (get(customer.getId()) == null) {
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

        if(customer != null) {
            em.remove(customer);
        }
    }
}
