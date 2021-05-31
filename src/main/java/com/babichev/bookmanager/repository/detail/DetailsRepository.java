package com.babichev.bookmanager.repository.detail;

import com.babichev.bookmanager.entity.Details;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * An interface describes the layer between the service and the database for the details entity
 */
@Repository
public interface DetailsRepository extends CrudRepository<Details, Integer> {

    /**
     * The method to get the details object by its id and book id
     * @param detailsId the id of the details
     * @param bookId the id of a book which ine the details are belong
     * @return the details object received from the database
     */
    Details getByIdAndBook_Id(int detailsId, int bookId);

    /**
     * The method to remove the details from the database by its id and @param bookId
     * @param detailsId the id of the details
     * @param bookId the id of a book which one the details are belong
     */
    @Transactional
    void deleteByIdAndBook_Id(int detailsId, int bookId);
}
