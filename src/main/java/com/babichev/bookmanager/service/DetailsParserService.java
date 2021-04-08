package com.babichev.bookmanager.service;

import com.babichev.bookmanager.entity.Book;
import com.babichev.bookmanager.entity.Details;

public interface DetailsParserService {
    Details findInfoOnPage(Book book);
}
