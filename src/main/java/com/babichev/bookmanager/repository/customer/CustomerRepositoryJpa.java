package com.babichev.bookmanager.repository.customer;

import com.babichev.bookmanager.entity.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Customer> getCustomerByUsername(String name) {
        Query get_customer_from_c_by_username = em.createQuery("SELECT c FROM Customer c WHERE c.login=:name")
                .setParameter("name", name);

        List resultList = get_customer_from_c_by_username.getResultList();

        return resultList.stream()
                .findFirst();
    }
}
