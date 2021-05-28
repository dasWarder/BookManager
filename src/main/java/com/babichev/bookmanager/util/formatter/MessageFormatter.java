package com.babichev.bookmanager.util.formatter;

import com.babichev.bookmanager.entity.Message;

import static com.babichev.bookmanager.service.email.MailSenderServiceImpl.AUTHOR;


/**
 * The class with methods to format email messages
 */
public class MessageFormatter {

    /**
     * The method to format greeting message for a new customer
     * @param email the email of a customer where the message will be send
     * @return the message object ready to send
     */
    public static Message formatGreetingMessage(String email) {
        Message message = new Message();

        String body = String.format("Hello!\n\n" +
                "Thank you for registration to our service! Our team hope that You will enjoy using it!\n\n" +
                "Have a nice day,\n" +
                "%s", AUTHOR);
        message.setTo(email);
        message.setTopic("Greeting!");
        message.setBody(body);

        return message;
    }
}
