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


/**
 * An implementation of CustomerRepository interface
 */
@Repository
public class CustomerRepositoryJpa implements CustomerRepository {

    /**
     * An object of entity manager to interact with the database
     */
    @PersistenceContext
    private EntityManager em;


    /**
     * The method that implements to save command
     * @param customer an entity of the customer, that must be stored to the database
     * @return the object of customer stored to the database
     */
    @Override
    @Transactional
    public Customer add(Customer customer) {
        if(isNull(customer.getId())) {
            /**
             * @see EntityManager#persist(Object)
             */
            em.persist(customer);
            return customer;
        } else if (isNull(get(customer.getId()))) {
            return null;
        }

        /**
         * @see EntityManager#merge(Object)
         */
        return em.merge(customer);
    }


    /**
     * The method that implements to get command
     * @param customerId the ID of the customer, that must be gotten from the database
     * @return the object of a customer from the database
     */
    @Override
    @Transactional
    public Customer get(int customerId) {
        /**
         * @see EntityManager#find(Class, Object)
         */
        return em.find(Customer.class, customerId);
    }

    /**
     * The method that implements to remove command
     * @param customerId the ID of a customer, that must be removed
     */
    @Override
    @Transactional
    public void remove(int customerId) {
        Customer customer = get(customerId);

        /**
         * @see EntityManager#remove(Object)
         */
        em.remove(customer);
    }

    /**
     * The method that implements to getByUsername command
     * @param name the name of a possible customer
     * @return the optional object of a customer or null
     */
    @Override
    public Optional<Customer> getCustomerByUsername(String name) {
        Query get_customer_from_c_by_username = em.createQuery("SELECT c FROM Customer c WHERE c.login=:name")
                .setParameter("name", name);

        List resultList = get_customer_from_c_by_username.getResultList();

        return resultList.stream()
                .findFirst();
    }
}
