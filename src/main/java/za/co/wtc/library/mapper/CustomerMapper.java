package za.co.wtc.library.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import za.co.wtc.library.dto.AddressDTO;
import za.co.wtc.library.dto.CustomerDto;
import za.co.wtc.library.model.Address;
import za.co.wtc.library.model.Customer;

@Component
public class CustomerMapper {

  public CustomerDto toDto(Customer customer) {
    if (customer == null) {
      return null;
    }
    CustomerDto customerDto = new CustomerDto();
    customerDto.setId(customer.getId());
    customerDto.setName(customer.getName());
    customerDto.setSurname(customer.getSurname());
    customerDto.setTitle(customer.getTitle());
    customerDto.setMemberShipExpireDate(customer.getMemberShipExpireDate());
    customerDto.setMemberShipStartDate(customer.getMemberShipStartDate());
    customerDto.setEmail(customer.getEmail());

    Set<AddressDTO> addressDTOS = customer.getAddresses().stream()
        .map(address -> {
          AddressDTO addressDto = new AddressDTO();
          addressDto.setId(address.getId());
          addressDto.setAddress1(address.getAddressLine1());
          addressDto.setAddress2(address.getAddressLine2());
          addressDto.setPostalCode(address.getPostalCode()); //TODO: strange stuff fix the bug
          return addressDto;
        }).collect(Collectors.toSet());

    customerDto.setAddressDTOS(addressDTOS);
    return customerDto;
  }


  public Customer toEntity(CustomerDto customerDto) {
    Customer customer = new Customer();
    customer.setId(customerDto.getId());
    customer.setName(customerDto.getName());
    customer.setEmail(customerDto.getEmail());
    customer.setSurname(customerDto.getSurname());
    customer.setTitle(customerDto.getTitle());
    customer.setIdNumber(customerDto.getIdNumber());
    customer.setMemberShipStartDate(customerDto.getMemberShipStartDate());
    customer.setMemberShipExpireDate(customerDto.getMemberShipExpireDate());

    Set<AddressDTO> addressesDto = customerDto.getAddressDTOS();
    if (addressesDto != null) {
      Set<Address> addresses = addressesDto.stream()
          .map(addressDTO -> {
            Address address = new Address();
            address.setId(address.getId());
            address.setAddressLine1(addressDTO.getAddress1());
            address.setAddressLine2(addressDTO.getAddress2());
            address.setPostalCode(addressDTO.getPostalCode());
            address.setCustomer(customer);
            return address;
          }).collect(Collectors.toSet());
      customer.setAddresses(addresses);
    }
    return customer;
  }
}