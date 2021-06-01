package com.babichev.bookmanager.dto;


import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;


/**
 * DTO class to get a customer data from the view and create a new user with the 'USER' role
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    @Email(message = "The email that was entered not a valid one")
    @NotNull(message = "The email must be not null")
    @NotBlank(message = "The email must be not empty")
    @Size(min = 7, max = 50, message = "The email must be of valid length")
    private String login;

    @NotNull(message = "The password must be not null")
    @NotBlank(message = "The password must be not empty")
    @Size(min = 7, max = 55, message = "The password must be between 7 and 55 elements of length")
    private String password;

}
