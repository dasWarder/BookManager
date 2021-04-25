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
    public Details add(Details details, int bookId) {
        if(details != null) {
            return detailsRepository.add(details, bookId);
        }

        throw new NullPointerException("Entity couldn't be equals null");
    }

    @Override
    @Transactional
    public Details get(int detailsId, int bookId) {
        Details details = detailsRepository.get(detailsId, bookId);

        return details != null? details : null;
    }

    @Override
    @Transactional
    public void remove(int detailsId, int bookId) {
            detailsRepository.remove(detailsId, bookId);
    }
}
