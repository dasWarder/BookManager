package com.babichev.bookmanager.repository.detail;

import com.babichev.bookmanager.entity.Details;

/**
 * An interface describes the layer between the service and the database for the details entity
 */
public interface DetailsRepository {

    /**
     * The method to store a new details to the database
     * @param details an entity of the details, that must be stored to the database
     * @param bookId the id of a book which one the details are belong
     * @return a details object with id, stored to the database
     */
    Details add(Details details, int bookId);

    /**
     * The method to get details from the database by its ID
     * @param detailsId the ID of the details, that must be gotten form the database
     * @param bookId the ID of a book which one the details are belong
     * @return the details from the database
     */
    Details get(int detailsId, int bookId);

    /**
     * The method to remove details from the database by its ID
     * @param detailsId the ID of details, that must be removed
     * @param bookId the ID of a book which one the details are belong
     */
    void remove(int detailsId, int bookId);
}
