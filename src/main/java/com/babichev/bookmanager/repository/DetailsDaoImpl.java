package com.babichev.bookmanager.repository;

import com.babichev.bookmanager.entity.Book;
import com.babichev.bookmanager.entity.Details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

@Repository
public class DetailsDaoImpl implements DetailsDao {

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

    @Transactional
    @Override
    public Details get(int details_id, int book_id) {
        Details details = em.find(Details.class, details_id);
        return details != null && details.getBook().getId() == book_id? details : null;
    }
}
