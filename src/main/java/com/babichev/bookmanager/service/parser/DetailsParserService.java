package com.babichev.bookmanager.service.parser;

import com.babichev.bookmanager.entity.Book;
import com.babichev.bookmanager.entity.Details;

/**
 * The interface with behavior of a parser service
 */
public interface DetailsParserService {
    /**
     * The method to find details information about a customer's book
     * @param book the book object for which the details must be founded
     * @return the details object for @param book
     */
    Details findInfoOnPage(Book book);
}
