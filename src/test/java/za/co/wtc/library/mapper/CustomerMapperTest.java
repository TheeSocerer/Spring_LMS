package za.co.wtc.library.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.wtc.library.dto.AddressDTO;
import za.co.wtc.library.dto.CustomerDto;
import za.co.wtc.library.model.Address;
import za.co.wtc.library.model.Customer;

@SpringBootTest()
class CustomerMapperTest {

  @Autowired
  private CustomerMapper customerMapper;


  @Test
  public void testToDto() {
    // Given
    Customer customer = new Customer();
    customer.setId(1L);
    customer.setName("John");
    customer.setSurname("Doe");
    customer.setTitle("Mr.");
    customer.setIdNumber("1234567890");
    customer.setEmail("john.doe@example.com");
    customer.setMemberShipStartDate(LocalDateTime.of(2023, 1, 1, 10, 0));
    customer.setMemberShipExpireDate(LocalDateTime.of(2024, 1, 1, 10, 0));

    Address address = new Address();
    address.setId(1L);
    address.setAddressLine1("123 Main St");
    address.setAddressLine2("Apt 4B");
    address.setPostalCode(12345);
    address.setCustomer(customer);

    Set<Address> addresses = new HashSet<>();
    addresses.add(address);
    customer.setAddresses(addresses);

    // When
    CustomerDto customerDto = customerMapper.toDto(customer);

    // Then
    assertNotNull(customerDto);
    assertEquals(customer.getId(), customerDto.getId());
    assertEquals(customer.getName(), customerDto.getName());
    assertEquals(customer.getSurname(), customerDto.getSurname());
    assertEquals(customer.getTitle(), customerDto.getTitle());
    assertEquals(customer.getMemberShipStartDate(), customerDto.getMemberShipStartDate());
    assertEquals(customer.getMemberShipExpireDate(), customerDto.getMemberShipExpireDate());
    assertEquals(customer.getEmail(), customerDto.getEmail());
    assertEquals(1, customerDto.getAddressDTOS().size());
  }

  @Test
  public void testToEntity() {
    // Given
    CustomerDto customerDto = new CustomerDto();
    customerDto.setId(1L);
    customerDto.setName("John");
    customerDto.setSurname("Doe");
    customerDto.setTitle("Mr.");
    customerDto.setIdNumber("1234567890");
    customerDto.setEmail("john.doe@example.com");
    customerDto.setMemberShipStartDate(LocalDateTime.of(2023, 1, 1, 10, 0));
    customerDto.setMemberShipExpireDate(LocalDateTime.of(2024, 1, 1, 10, 0));

    AddressDTO addressDto = new AddressDTO();
    addressDto.setId(1L);
    addressDto.setAddress1("123 Main St");
    addressDto.setAddress2("Apt 4B");
    addressDto.setPostalCode(12345);

    Set<AddressDTO> addressDTOS = new HashSet<>();
    addressDTOS.add(addressDto);
    customerDto.setAddressDTOS(addressDTOS);

    // When
    Customer customer = customerMapper.toEntity(customerDto);

    // Then
    assertNotNull(customer);
    assertEquals(customerDto.getId(), customer.getId());
    assertEquals(customerDto.getName(), customer.getName());
    assertEquals(customerDto.getSurname(), customer.getSurname());
    assertEquals(customerDto.getTitle(), customer.getTitle());
    assertEquals(customerDto.getMemberShipStartDate(), customer.getMemberShipStartDate());
    assertEquals(customerDto.getMemberShipExpireDate(), customer.getMemberShipExpireDate());
    assertEquals(customerDto.getEmail(), customer.getEmail());
    assertEquals(1, customer.getAddresses().size());
  }

  // todo add null test customerMapper.toDto(null)
  @Test
  public void  toDtoNull(){
    //Given 
    Customer customer = null;

    // When
    CustomerDto customerDto = customerMapper.toDto(customer);

    assertNull(customerDto);
  }
}