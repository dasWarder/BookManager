package com.babichev.bookmanager.service.email;

import com.babichev.bookmanager.entity.Message;


/**
 * The interface that describes behavior of a service for sending emails
 */
public interface MailSenderService {

    /**
     * The mail method for sending the email
     * @param message the message that must be send
     */
    void sendMessage(Message message);
}
