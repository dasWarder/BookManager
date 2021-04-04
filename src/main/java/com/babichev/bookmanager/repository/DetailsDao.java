package com.babichev.bookmanager.repository;

import com.babichev.bookmanager.entity.Details;

public interface DetailsDao {

    Details add(Details details, int book_id);
    Details get(int details_id, int book_id);
    void remove(int details_id, int book_id);
}
