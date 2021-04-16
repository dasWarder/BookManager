package com.babichev.bookmanager.service.detail;

import com.babichev.bookmanager.entity.Details;
import com.babichev.bookmanager.repository.detail.DetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DetailsServiceImpl implements DetailsService {

    private DetailsRepository detailsRepository;

    @Autowired
    public DetailsServiceImpl(DetailsRepository detailsDao) {
        this.detailsRepository = detailsDao;
    }

    @Override
    @Transactional
    public Details add(Details details, int book_id) {
        if(details != null) {
            return detailsRepository.add(details, book_id);
        }

        throw new NullPointerException("Entity couldn't be equals null");
    }

    @Override
    public Details get(int details_id, int book_id) {
        Details details = detailsRepository.get(details_id, book_id);

        return details != null? details : null;
    }

    @Override
    @Transactional
    public void remove(int details_id, int book_id) {
            detailsRepository.remove(details_id, book_id);
    }
}
