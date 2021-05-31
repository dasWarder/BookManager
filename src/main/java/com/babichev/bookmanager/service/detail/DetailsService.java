package com.babichev.bookmanager.service.detail;

import com.babichev.bookmanager.entity.Details;
import com.babichev.bookmanager.exception.BookNotFoundException;


/**
 * An interface that describes a behavior of a class on the service layer (between the controller and the repository)
 */
public interface DetailsService {

    /**
     * The method to validate a details object for storage it to the database
     * @param details a details object that must be validated
     * @param bookId the ID of a book which one the details are belong
     * @return the details object with ID stored to the database
     */
    Details add(Details details, int bookId) throws BookNotFoundException;

    /**
     * The method to validate a details object for receiving it from the database
     * @param detailsId the ID of a details object
     * @param bookId the ID of a book which one the details are belong
     * @return the details object from the database
     */
    Details get(int detailsId, int bookId);

    /**
     * The method to validate a details object for removing from the database
     * @param detailsId the ID of a details object
     * @param bookId the ID of a book which one the details are belong
     */
    void remove(int detailsId, int bookId);
}
