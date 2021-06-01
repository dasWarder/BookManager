package com.babichev.bookmanager.dto;


import lombok.Data;

@Data
public class BookSearchCriteria {

    private String title;

    private String author;

    private Integer from;

    private Integer to;
}
