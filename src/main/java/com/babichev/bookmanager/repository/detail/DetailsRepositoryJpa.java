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
    public Details add(Details details, int bookId) {
        details.setBook(em.getReference(Book.class, bookId));

        if(details.getId() == null) {
            em.persist(details);
            return details;
        } else if(get(details.getId(), bookId) == null){
            return null;
        }

        return em.merge(details);
    }

    @Override
    public Details get(int detailsId, int bookId) {
        Details details = em.find(Details.class, detailsId);
        return details != null && details.getBook().getId() == bookId? details : null;
    }

    @Override
    @Transactional
    public void remove(int detailsId, int bookId) {
        Details details = get(detailsId, bookId);
        em.remove(details);
    }
}
