package com.babichev.bookmanager.service.email;


import com.babichev.bookmanager.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * The implementation of the MailSender interface
 */
@Slf4j
@Service
public class MailSenderServiceImpl implements MailSenderService {

    /**
     * The bean of the JavaMailSender
     */
    private JavaMailSender javaMailSender;

    /**
     * The global field with the name of current service(the application)
     */
    public static final String AUTHOR = "bookManager@gmail.com";

    @Autowired
    public MailSenderServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * The implementation of the sendMessage command.
     * @see MailSenderService
     * @param message the message that must be send
     */
    @Override
    public void sendMessage(Message message) {
        log.info("Try to send a message from MailSenderService");

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(AUTHOR);
        mailMessage.setSubject(message.getTopic());
        mailMessage.setTo(message.getTo());
        mailMessage.setText(message.getBody());

        javaMailSender.send(mailMessage);

        log.info("The message was successfully send");
    }
}
