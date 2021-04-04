package com.babichev.bookmanager.service;

import com.babichev.bookmanager.entity.Details;
import com.babichev.bookmanager.repository.DetailsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DetailsServiceImpl implements DetailsService {

    private DetailsDao detailsDao;

    @Autowired
    public DetailsServiceImpl(DetailsDao detailsDao) {
        this.detailsDao = detailsDao;
    }

    @Override
    @Transactional
    public Details add(Details details, int book_id) {
        if(details != null) {
            return detailsDao.add(details, book_id);
        }

        throw new NullPointerException("Entity couldn't be equals null");
    }

    @Override
    public Details get(int details_id, int book_id) {
        Details details = detailsDao.get(details_id, book_id);

        return details != null? details : null;
    }

    @Override
    @Transactional
    public void remove(int details_id, int book_id) {
            detailsDao.remove(details_id, book_id);
    }
}
