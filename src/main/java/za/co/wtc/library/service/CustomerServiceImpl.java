package za.co.wtc.library.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.wtc.library.model.Customer;
import za.co.wtc.library.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);


  @Override
  public Customer findByIdNumber(String idNumber) {
    logger.info("search for customer with id {}", idNumber);
    try {
      Customer customer = customerRepository.findByIdNumber(idNumber);
      if (customer != null) {
        logger.info("Found customer with id-number {} details {}", idNumber, customer);
      }
      return customer;
    } catch (Exception exception) {
      String message = "Error while searching for customer with id-number " + idNumber
          + exception.getMessage();
      logger.error(message, exception);
      throw new RuntimeException(message, exception);
    }
  }

  @Override
  public Customer findByEmail(String email){
    logger.info("search for customer with email {}", email);
    try{
      Customer customer = customerRepository.findByEmail(email);
      if (customer != null){
        logger.info("Found customer with email {} details {}", email,customer);
      }
      return customer;

    }catch (Exception exception){
      String message = "Error while searching for customer with email " + email
          + exception.getMessage();
      logger.error(message, exception);
      throw new RuntimeException(message, exception);

    }

  }

  @Override
  public Customer registerCustomer(Customer customer){
    Customer existingCustomer = customerRepository.findByIdNumber(customer.getIdNumber());
    Customer existingCustomerEmail = customerRepository.findByEmail(customer.getEmail());
    if (existingCustomer != null) {
        throw new RuntimeException("Customer with ID number already exists");
    }
    if (existingCustomerEmail != null) {
      throw new RuntimeException("Customer with email already exists");
  }
    customer.setMemberShipStartDate(LocalDateTime.now());
    customer.setMemberShipExpireDate(LocalDateTime.now().plusYears(1));

    return customerRepository.save(customer);
  }

  @Override
  public Customer editCustomerDetails(Customer newCustomerDetails){
    Customer existingCustomer = customerRepository.findByIdNumber(newCustomerDetails.getIdNumber());
    if(existingCustomer != null){
      // Update customer details
      existingCustomer.setName(newCustomerDetails.getName());
      existingCustomer.setSurname(newCustomerDetails.getSurname());
      existingCustomer.setTitle(newCustomerDetails.getTitle());
      existingCustomer.setIdNumber(newCustomerDetails.getIdNumber());
      existingCustomer.setEmail(newCustomerDetails.getEmail());
      existingCustomer.setMemberShipStartDate(newCustomerDetails.getMemberShipStartDate());
      existingCustomer.setMemberShipExpireDate(newCustomerDetails.getMemberShipExpireDate());
      return customerRepository.save(existingCustomer);
    }else{
      throw new RuntimeException("Customer with ID number already exists");
    }
  }
  
}
