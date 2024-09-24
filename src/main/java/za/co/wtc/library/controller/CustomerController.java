package za.co.wtc.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.co.wtc.library.dto.CustomerDto;
import za.co.wtc.library.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @RequestMapping(method = RequestMethod.GET, value = "email/{email}", produces = {
      "application/json"})
  public ResponseEntity<CustomerDto> findByEmail(@PathVariable("email") String email) {
    CustomerDto customerDto = customerService.findByEmail(email);
    return new ResponseEntity<>(customerDto, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET, value = "id-number/{idNumber}", produces = {
      "application/json"})
  public ResponseEntity<CustomerDto> findByIdNumber(@PathVariable("idNumber") String idNumber) {
    CustomerDto customerDto = customerService.findByIdNumber(idNumber);
    return new ResponseEntity<>(customerDto, HttpStatus.OK);
  }

  // Register a new customer (POST request)
  @RequestMapping(method = RequestMethod.POST, value = "/add", consumes = {
      "application/json"}, produces = {"application/json"})
  public ResponseEntity<CustomerDto> registerCustomer(@RequestBody CustomerDto customer) {
    CustomerDto registeredCustomer = customerService.registerCustomer(customer);
    return new ResponseEntity<>(registeredCustomer, HttpStatus.CREATED);
  }

   // TODO: implement this method correctly.
 // Register a new customer (POST request)
 @RequestMapping(method = RequestMethod.POST, value = "/edit", produces = {"application/json"})
 public ResponseEntity<CustomerDto> editCustomerDetails(@RequestBody CustomerDto customer) {
   CustomerDto editedCustomer = customerService.editCustomerDetails(customer);
   return new ResponseEntity<>(editedCustomer, HttpStatus.OK);
 }
}
