package com.babichev.bookmanager.service.detail;

import com.babichev.bookmanager.entity.Details;
import com.babichev.bookmanager.repository.customer.CustomerRepository;
import com.babichev.bookmanager.repository.detail.DetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


/**
 * The service class that implements DetailsService interface
 */
@Service
public class DetailsServiceImpl implements DetailsService {

    /**
     * The field with a details repository bean
     * @see DetailsRepository
     */
    private DetailsRepository detailsRepository;

    @Autowired
    public DetailsServiceImpl(DetailsRepository detailsDao) {
        this.detailsRepository = detailsDao;
    }

    /**
     * The method to validate a details object for to save command
     * @param details a details object that must be validated
     * @param bookId the ID of a book which one the details are belong
     * @return the details object with ID stored to the database
     */
    @Override
    @Transactional
    public Details add(Details details, int bookId) {
        Assert.notNull(details, "details must not be null");

        /**
         * @see DetailsRepository#add(Details, int)
         */
        return detailsRepository.add(details, bookId);
    }

    /**
     * The method to validate a details object for to get command
     * @param detailsId the ID of a details object
     * @param bookId the ID of a book which one the details are belong
     * @return the details object from the database
     */
    @Override
    @Transactional
    public Details get(int detailsId, int bookId) {
        /**
         * @see DetailsRepository#get(int, int)
         */
        return detailsRepository.get(detailsId, bookId);
    }

    /**
     * The method to validate a details object for to remove command
     * @param detailsId the ID of a details object
     * @param bookId the ID of a book which one the details are belong
     */
    @Override
    @Transactional
    public void remove(int detailsId, int bookId) {
        /**
         * @see DetailsRepository#remove(int, int)
         */
        detailsRepository.remove(detailsId, bookId);
    }
}
