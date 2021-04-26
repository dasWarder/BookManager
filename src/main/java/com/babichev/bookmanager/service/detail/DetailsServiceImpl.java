package com.babichev.bookmanager.service.detail;

import com.babichev.bookmanager.entity.Details;
import com.babichev.bookmanager.repository.detail.DetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


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
        Assert.notNull(details, "details must not be null");
        return detailsRepository.add(details, bookId);
    }

    @Override
    @Transactional
    public Details get(int detailsId, int bookId) {
        return detailsRepository.get(detailsId, bookId);
    }

    @Override
    @Transactional
    public void remove(int detailsId, int bookId) {
        detailsRepository.remove(detailsId, bookId);
    }
}
