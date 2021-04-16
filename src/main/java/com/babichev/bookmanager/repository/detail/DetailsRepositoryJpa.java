package com.babichev.bookmanager.repository.detail;

import com.babichev.bookmanager.entity.Book;
import com.babichev.bookmanager.entity.Details;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class DetailsRepositoryJpa implements DetailsRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public Details add(Details details, int book_id) {
        details.setBook(em.getReference(Book.class, book_id));

        if(details.getId() == null) {
            em.persist(details);
            return details;
        } else if(get(details.getId(), book_id) == null){
            return null;
        }

        return em.merge(details);
    }

    @Override
    public Details get(int details_id, int book_id) {
        Details details = em.find(Details.class, details_id);
        return details != null && details.getBook().getId() == book_id? details : null;
    }

    @Override
    @Transactional
    public void remove(int details_id, int book_id) {
        Details details = get(details_id, book_id);
        em.remove(details);
    }
}
