package com.babichev.bookmanager.service;

import com.babichev.bookmanager.repository.book.BookRepository;
import com.babichev.bookmanager.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public abstract class AbstractBookedService {

    protected BookRepository bookRepository;

    protected SecurityUtil securityUtil;

    @Autowired
    public AbstractBookedService(BookRepository bookRepository, SecurityUtil securityUtil) {
        this.bookRepository = bookRepository;
        this.securityUtil = securityUtil;
    }

    public Integer getLoggedUserId() {
        return securityUtil.getAuthUserId();
    }
}
