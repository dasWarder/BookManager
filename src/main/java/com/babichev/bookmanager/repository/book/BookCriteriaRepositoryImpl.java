package com.babichev.bookmanager.repository.book;


import com.babichev.bookmanager.dto.BookSearchCriteria;
import com.babichev.bookmanager.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Slf4j
@Repository
public class BookCriteriaRepositoryImpl implements BookCriteriaRepository {

    private EntityManager entityManager;
    private CriteriaBuilder criteriaBuilder;

    @Autowired
    public BookCriteriaRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Book> findAllWithFilters(String sortBy, int customerId,
                                         Pageable pageable,
                                         BookSearchCriteria searchCriteria) {
        log.info("Try to find all books with customerId = {}, and title={}, author ={}, date from={}, get to={}",
                customerId,
                searchCriteria.getTitle(),
                searchCriteria.getAuthor(),
                searchCriteria.getFrom(),
                searchCriteria.getTo());

        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> bookRoot = criteriaQuery.from(Book.class);

        Predicate predicate = getPredicate(customerId, searchCriteria, bookRoot);

        criteriaQuery.where(predicate);

        TypedQuery<Book> typedQuery = entityManager.createQuery(criteriaQuery);
        log.info("Create query for entityManager");

        typedQuery.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        typedQuery.setMaxResults(pageable.getPageSize());
        Pageable sortPageable = getPageable(sortBy, pageable);

        long booksCount = getBooksCount(predicate);

        log.info("Get books count = {}", booksCount);

        return new PageImpl<>(typedQuery.getResultList(), sortPageable, booksCount);
    }

    private Pageable getPageable(String sortBy, Pageable pageable) {
        Sort sort = Sort.by(Sort.Direction.DESC, sortBy);

        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
    }


    private Predicate getPredicate(int customerId, BookSearchCriteria searchCriteria,
                                   Root<Book> bookRoot) {
        log.info("Try to get predicates");
        List<Predicate> predicates = new ArrayList<>();

        if(Objects.nonNull(customerId)) {
            predicates.add(
                    criteriaBuilder.equal(bookRoot.get("customer").get("id"), customerId)
            );
            log.info("Add customerId predicate");
        }

        if(Objects.nonNull(searchCriteria.getTitle()) && !("".equals(searchCriteria.getTitle()))) {
            predicates.add(
                    criteriaBuilder.like(bookRoot.get("name"), "%" + searchCriteria.getTitle() + "%")
            );
            log.info("Add title predicate");
        }

        if(Objects.nonNull(searchCriteria.getAuthor())&& !("".equals(searchCriteria.getAuthor()))) {
            predicates.add(
                    criteriaBuilder.like(bookRoot.get("author"), "%" + searchCriteria.getAuthor() + "%")
            );
            log.info("Add author predicate");
        }

        if(Objects.nonNull(searchCriteria.getFrom()) && !("".equals(searchCriteria.getFrom()))) {
            predicates.add(
                    criteriaBuilder.ge(bookRoot.get("year"), searchCriteria.getFrom())
            );
            log.info("Add from predicate");
        }

        if(Objects.nonNull(searchCriteria.getTo()) && !("".equals(searchCriteria.getTo()))) {
            predicates.add(
                    criteriaBuilder.le(bookRoot.get("year"), searchCriteria.getTo())
            );
            log.info("Add to predicate");
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private long getBooksCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Book> countRoot = countQuery.from(Book.class);
        countQuery.select(criteriaBuilder.count(countRoot))
                .where(predicate);

        return entityManager.createQuery(countQuery).getSingleResult();
    }

}
