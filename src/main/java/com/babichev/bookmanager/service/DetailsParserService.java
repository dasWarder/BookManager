package com.babichev.bookmanager.service;

import com.babichev.bookmanager.entity.Book;
import com.babichev.bookmanager.entity.Details;

public interface InfoParserService {
    Details findInfoOnPage(Book book);
}
