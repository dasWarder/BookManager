package com.babichev.bookmanager.repository.detail;

import com.babichev.bookmanager.entity.Details;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * An interface describes the layer between the service and the database for the details entity
 */
public interface DetailsRepository extends CrudRepository<Details, Integer> {

    Details getByIdAndBook_Id(int detailsId, int bookId);

    @Transactional
    void deleteByIdAndBook_Id(int detailsId, int bookId);
}
