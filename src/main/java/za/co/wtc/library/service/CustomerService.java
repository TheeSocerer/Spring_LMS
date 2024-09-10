package za.co.wtc.library.service;

import za.co.wtc.library.dto.CustomerDto;
import za.co.wtc.library.model.Customer;

public interface CustomerService {

  CustomerDto findByIdNumber(String idNumber);

  Customer findByEmail(String email);

  Customer registerCustomer(Customer customer);

  CustomerDto editCustomerDetails(String idNumber,Customer customer);
}
