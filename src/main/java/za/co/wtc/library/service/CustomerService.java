package za.co.wtc.library.service;

import za.co.wtc.library.dto.CustomerDto;

public interface CustomerService {

  CustomerDto findByIdNumber(String idNumber);

  CustomerDto findByEmail(String email);

  CustomerDto registerCustomer(CustomerDto customerDto);

  CustomerDto editCustomerDetails(CustomerDto customerDto);
}
