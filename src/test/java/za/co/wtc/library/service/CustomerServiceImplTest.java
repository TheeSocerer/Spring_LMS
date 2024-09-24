package za.co.wtc.library.service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import za.co.wtc.library.dto.AddressDTO;
import za.co.wtc.library.dto.CustomerDto;

@Sql("classpath:db/customers.sql")
@SpringBootTest()
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class CustomerServiceImplTest {

  @Autowired
  public CustomerServiceImpl customerService;

  @Test
  public void testFindByIdNumberSuccess() {
    CustomerDto customerDto = customerService.findByIdNumber("1234567890");
    assertNotNull(customerDto);
    assertEquals("John", customerDto.getName());
    //TODO: assert other fields
    assertEquals("Doe", customerDto.getSurname());
    assertEquals("john.doe@example.com", customerDto.getEmail());

    assertEquals("2023-01-01T10:00", customerDto.getMemberShipStartDate().toString());
  }

  @Test
  public void testFindByIdNumberNot() {
    CustomerDto customerDto = customerService.findByIdNumber("99999999");
    assertNull(customerDto);
  }

  @Test
  public void testRegisterCustomer() {
    CustomerDto customerDto = new CustomerDto();
    customerDto.setName("John");
    customerDto.setSurname("Doe");
    customerDto.setTitle("Mr.");
    customerDto.setIdNumber("7888665333");
    customerDto.setEmail("john@example.com");
    // customerDto.setMemberShipStartDate(LocalDateTime.of(2023, 1, 1, 10, 0));
    // customerDto.setMemberShipExpireDate(LocalDateTime.of(2024, 1, 1, 10, 0));

    AddressDTO addressDto = new AddressDTO();
    addressDto.setId(1L);
    addressDto.setAddress1("123 Main St");
    addressDto.setAddress2("Apt 4B");
    addressDto.setPostalCode(12345);

    Set<AddressDTO> addressDTOS = new HashSet<>();
    addressDTOS.add(addressDto);

    customerDto.setAddressDTOS(addressDTOS);


    CustomerDto customerDto1 = customerService.registerCustomer(customerDto);

    assertNotNull(customerDto1);
    assertNotNull(customerDto1.getId());
    assertEquals(customerDto.getName(), customerDto1.getName());
    assertEquals(customerDto.getSurname(), customerDto1.getSurname());
    assertEquals(customerDto.getTitle(), customerDto1.getTitle());

    // TODO: find way of testing this
   assertEquals(customerDto.getMemberShipStartDate().truncatedTo(ChronoUnit.MINUTES),
                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
   assertEquals(customerDto.getMemberShipExpireDate().truncatedTo(ChronoUnit.MINUTES), 
                LocalDateTime.now().plusYears(1).truncatedTo(ChronoUnit.MINUTES));

    assertEquals(customerDto.getEmail(), customerDto1.getEmail());
    assertEquals(1, customerDto1.getAddressDTOS().size());
    // todo do some asserts here
    AddressDTO addressDto2 = customerDto1.getAddressDTOS().stream().findFirst().orElse(null);

      assert addressDto2 != null;
      assertEquals(addressDto.getAddress1(), addressDto2.getAddress1());
    assertEquals(addressDto.getAddress2(), addressDto2.getAddress2());

    // data was being lost here in the address mapper
    assertEquals(addressDto.getPostalCode(),addressDto2.getPostalCode());

    


  }


  @Test
  public void testRegisterCustomerAlreadyExistById() {
    CustomerDto customerDto = new CustomerDto();
    customerDto.setName("John");
    customerDto.setSurname("Doe");
    customerDto.setTitle("Mr.");
    customerDto.setIdNumber("1234567890");
    assertThrows(RuntimeException.class, () -> customerService.registerCustomer(customerDto));

  }

  // todo test by duplicate email address
  @Test
  public void testRegisterCustomerAlreadyExistByEmail() {
    CustomerDto customerDto = new CustomerDto();
    customerDto.setName("John");
    customerDto.setSurname("Doe");
    customerDto.setTitle("Mr.");
    customerDto.setEmail("alice.johnson@example.com");
    assertThrows(RuntimeException.class, () -> customerService.registerCustomer(customerDto));
  }

  // todo add editCustomerDetails test method or methods
  @Test
  public void testEditCustomerDetailsSuccess(){
    CustomerDto customerDto = new CustomerDto();
    customerDto.setName("Tshepo");
    customerDto.setSurname("Shiburi");
    customerDto.setTitle("Mr.");
    customerDto.setIdNumber("1122334455");
    customerDto.setEmail("Tshepo@example.com");
    // customerDto.setMemberShipStartDate(LocalDateTime.of(2023, 1, 1, 10, 0));
    // customerDto.setMemberShipExpireDate(LocalDateTime.of(2024, 1, 1, 10, 0));

    AddressDTO addressDto = new AddressDTO();
    addressDto.setId(1L);
    addressDto.setAddress1("123 Main St");
    addressDto.setAddress2("Apt 4B");
    addressDto.setPostalCode(123457);

    Set<AddressDTO> addressDTOS = new HashSet<>();
    addressDTOS.add(addressDto);

    customerDto.setAddressDTOS(addressDTOS);


    CustomerDto customerDto1 = customerService.editCustomerDetails(customerDto);
    assertNotNull(customerDto1);
    assertEquals("Tshepo", customerDto1.getName());

  }



}