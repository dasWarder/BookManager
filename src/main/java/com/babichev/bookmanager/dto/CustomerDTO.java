package com.babichev.bookmanager.dto;


import lombok.*;


/**
 * DTO class to get a customer data from the view and create a new user with the 'USER' role
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private String login;
    private String password;
}
