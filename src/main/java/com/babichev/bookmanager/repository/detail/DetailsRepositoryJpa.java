package com.babichev.bookmanager.repository.detail;

import com.babichev.bookmanager.entity.Book;
import com.babichev.bookmanager.entity.Details;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static java.util.Objects.isNull;


/**
 * An implementation of DetailsRepository interface
 */
@Repository
public class DetailsRepositoryJpa implements DetailsRepository {

    /**
     * An object of entity manager to interact with the database
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * The method that implements to save command
     * @param details an entity of the details, that must be stored to the database
     * @param bookId the id of a book which one the details are belong
     * @return the details objects from the database
     */
    @Override
    @Transactional
    public Details add(Details details, int bookId) {
        /**
         * @see EntityManager#getReference(Class, Object)
         */
        details.setBook(em.getReference(Book.class, bookId));

        if (isNull(details.getId())) {
            /**
             * @see EntityManager#persist(Object)
             */
            em.persist(details);
            return details;
        } else if(isNull(get(details.getId(), bookId))){
            return null;
        }

        /**
         * @see EntityManager#merge(Object)
         */
        return em.merge(details);
    }

    /**
     * The method that implements to get command
     * @param detailsId the ID of the details, that must be gotten form the database
     * @param bookId the ID of a book which one the details are belong
     * @return the object of details form the database
     */
    @Override
    public Details get(int detailsId, int bookId) {

        /**
         * @see EntityManager#find(Class, Object)
         */
        Details details = em.find(Details.class, detailsId);

        return !isNull(details)
                && details.getBook().getId() == bookId? details : null;
    }

    /**
     * The method that implements to remove command
     * @param detailsId the ID of details, that must be removed
     * @param bookId the ID of a book which one the details are belong
     */
    @Override
    @Transactional
    public void remove(int detailsId, int bookId) {
        Details details = get(detailsId, bookId);

        /**
         * @see EntityManager#remove(Object)
         */
        em.remove(details);
    }
}
