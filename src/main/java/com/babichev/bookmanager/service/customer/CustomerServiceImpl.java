package com.babichev.bookmanager.service.customer;


import com.babichev.bookmanager.entity.Customer;
import com.babichev.bookmanager.exception.CustomerNotFoundException;
import com.babichev.bookmanager.repository.book.BookRepository;
import com.babichev.bookmanager.repository.customer.CustomerRepository;
import com.babichev.bookmanager.service.email.MailSenderService;
import com.babichev.bookmanager.util.formatter.MessageFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.babichev.bookmanager.util.formatter.MessageFormatter.formatGreetingMessage;

/**
 * The service class that implements CustomerService interface
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    /**
     * The field with a customer repository bean
     * @see CustomerRepository
     */
    private CustomerRepository customerRepository;

    /**
     * The field with a mail sender service bean
     * @see MailSenderService
     */
    private MailSenderService mailSenderService;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, MailSenderService mailSenderService) {
        this.customerRepository = customerRepository;
        this.mailSenderService = mailSenderService;
    }

    /**
     * The method to validate a customer for to save command
     * @param customer a customer object that must be validated
     * @return the customer object with ID stored to the database
     */
    @Override
    @Transactional
    public Customer add(Customer customer) {
        Assert.notNull(customer, "customer must not be null");
        if(customer.getId() == null) {
            mailSenderService.sendMessage(
                    formatGreetingMessage(customer.getLogin()));
        }

        /**
         * @see CustomerRepository#add(Customer)
         */
        return customerRepository.add(customer);
    }

    /**
     * The method to validate a customer for to get command
     * @param customerId
     * @return
     */
    @Override
    @Transactional
    public Customer get(int customerId) {

        /**
         * @see CustomerRepository#get(int)
         */
        return customerRepository.get(customerId);
    }

    /**
     * The method to validate a customer for to remove command
     * @param customerId the ID of a customer
     */
    @Override
    @Transactional
    public void remove(int customerId) {

        /**
         * @see CustomerRepository#remove(int)
         */
        customerRepository.remove(customerId);
    }

    /**
     * The method to validate a customer for to getByUsername command
     * @param name the name of a customer that must be received from the database
     * @return the customer object received from the repository layer
     */
    @Override
    @Transactional
    public Customer getByUsername(String name) {

        /**
         * @see CustomerRepository#getCustomerByUsername(String)
         */
        Optional<Customer> customerByUsername = customerRepository.getCustomerByUsername(name);

        if(!customerByUsername.isPresent()) {
            throw new CustomerNotFoundException(
                    String.format("Customer with name=%s not found", name));
        }

        return customerByUsername.get();
    }

}
