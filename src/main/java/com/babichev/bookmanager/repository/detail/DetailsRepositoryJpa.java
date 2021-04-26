package com.babichev.bookmanager.repository.detail;

import com.babichev.bookmanager.entity.Book;
import com.babichev.bookmanager.entity.Details;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static java.util.Objects.isNull;

@Repository
public class DetailsRepositoryJpa implements DetailsRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public Details add(Details details, int bookId) {
        details.setBook(em.getReference(Book.class, bookId));

        if (isNull(details.getId())) {
            em.persist(details);
            return details;
        } else if(isNull(get(details.getId(), bookId))){
            return null;
        }

        return em.merge(details);
    }

    @Override
    public Details get(int detailsId, int bookId) {
        Details details = em.find(Details.class, detailsId);

        return !isNull(details)
                && details.getBook().getId() == bookId? details : null;
    }

    @Override
    @Transactional
    public void remove(int detailsId, int bookId) {
        Details details = get(detailsId, bookId);
        em.remove(details);
    }
}
