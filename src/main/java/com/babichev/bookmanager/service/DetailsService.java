package com.babichev.bookmanager.service;

import com.babichev.bookmanager.entity.Details;

public interface DetailsService {
    Details add(Details details, int book_id);
    Details get(int details_id, int book_id);
}
