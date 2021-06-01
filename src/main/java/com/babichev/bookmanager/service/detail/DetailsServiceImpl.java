package com.babichev.bookmanager.service.detail;

import com.babichev.bookmanager.entity.Book;
import com.babichev.bookmanager.entity.Details;
import com.babichev.bookmanager.exception.BookNotFoundException;
import com.babichev.bookmanager.repository.book.BookRepository;
import com.babichev.bookmanager.repository.detail.DetailsRepository;
import com.babichev.bookmanager.service.AbstractBookedService;
import com.babichev.bookmanager.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


/**
 * The service class that implements DetailsService interface
 */
@Service
public class DetailsServiceImpl extends AbstractBookedService implements DetailsService {

    /**
     * The field with a details repository bean
     * @see DetailsRepository
     */
    private DetailsRepository detailsRepository;

    @Autowired
    public DetailsServiceImpl(BookRepository bookRepository, SecurityUtil securityUtil, DetailsRepository detailsRepository) {
        super(bookRepository, securityUtil);
        this.detailsRepository = detailsRepository;
    }

    /**
     * The method to validate a details object for to save command
     * @param details a details object that must be validated
     * @param bookId the ID of a book which one the details are belong
     * @return the details object with ID stored to the database
     */
    @Override
    @Transactional
    public Details add(Details details, int bookId) throws BookNotFoundException {
        Book book = bookRepository.getBookByIdAndCustomer_Id(bookId, getLoggedUserId());

        Assert.notNull(details, "details must not be null");
        Assert.notNull(book, "book must be not null");

        if(details.getBook() == null) {
            details.setBook(book);

            /**
             * @see DetailsRepository#add(Details, int)
             */
            return detailsRepository.save(details);
        }

        Book currentBook = details.getBook();

        if(currentBook.getId() == bookId) {
            return detailsRepository.save(details);
        }

        throw new BookNotFoundException("Wrong book id");
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
        return detailsRepository.getByIdAndBook_Id(detailsId, bookId);
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
        detailsRepository.deleteByIdAndBook_Id(detailsId, bookId);
    }
}
