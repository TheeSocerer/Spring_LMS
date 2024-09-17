package za.co.wtc.library.service;

import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.wtc.library.dto.CustomerDto;
import za.co.wtc.library.mapper.CustomerMapper;
import za.co.wtc.library.model.Customer;
import za.co.wtc.library.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private CustomerMapper customerMapper;

  private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

  @Override
  public CustomerDto findByIdNumber(String idNumber) {
    logger.info("search for customer with id {}", idNumber);
    try {
      Customer customer = customerRepository.findByIdNumber(idNumber);
      if (customer != null) {
        logger.info("Found customer with id-number {} details {}", idNumber, customer);
      }
      return customerMapper.toDto(customer);
    } catch (Exception exception) {
      String message = "Error while searching for customer with id-number " + idNumber
          + exception.getMessage();
      logger.error(message, exception);
      throw new RuntimeException(message, exception);
    }
  }

  @Override
  public Customer findByEmail(String email) {
    // todo complete method and add unit tests
    return null;
  }

  @Override
  public CustomerDto registerCustomer(CustomerDto customerDto) {
    Customer customer = customerMapper.toEntity(customerDto);

    Customer existingCustomer = customerRepository.findByIdNumber(customer.getIdNumber());
    Customer existingCustomerEmail = customerRepository.findByEmail(customer.getEmail());
    if (existingCustomer != null) {
      throw new RuntimeException("Customer with ID number already exists");
    }
    if (existingCustomerEmail != null) {
      throw new RuntimeException("Customer with email already exists");
    }
    customerDto.setMemberShipStartDate(LocalDateTime.now());
    customerDto.setMemberShipExpireDate(LocalDateTime.now().plusYears(1));
    customer = customerRepository.save(customer);
    return customerMapper.toDto(customer);
  }

  @Override
  public CustomerDto editCustomerDetails(CustomerDto customerDto) {
    Customer existingCustomer = customerRepository.findByIdNumber(customerDto.getIdNumber());
    if (existingCustomer != null) {
      // Update customer details
      existingCustomer.setName(customerDto.getName());
      existingCustomer.setSurname(customerDto.getSurname());
      existingCustomer.setTitle(customerDto.getTitle());
      existingCustomer.setIdNumber(customerDto.getIdNumber());
      existingCustomer.setEmail(customerDto.getEmail());
      return customerMapper.toDto(customerRepository.save(existingCustomer));
    } else {
      throw new RuntimeException("Customer with ID number does not exists");
    }
  }
}
