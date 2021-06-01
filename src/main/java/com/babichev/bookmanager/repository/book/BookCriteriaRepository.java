package com.babichev.bookmanager.repository.book;

import com.babichev.bookmanager.dto.BookSearchCriteria;
import com.babichev.bookmanager.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookCriteriaRepository {

    Page<Book> findAllWithFilters(String sortBy,
                                  int customerId,
                                  Pageable pageable,
                                  BookSearchCriteria searchCriteria);
}
