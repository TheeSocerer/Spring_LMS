package za.co.wtc.library.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import za.co.wtc.library.dto.CustomerDto;
import za.co.wtc.library.exception.LibraryExceptionHandler;
import za.co.wtc.library.service.CustomerService;

@WebMvcTest(CustomerController.class)
@ContextConfiguration(classes = {CustomerController.class, LibraryExceptionHandler.class})
class CustomerControllerTest {


  @MockBean
  private CustomerService customerService;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void testFindByIdNumberSuccess() throws Exception {

    Mockito.when(customerService.findByIdNumber(Mockito.anyString()))
        .thenReturn(new CustomerDto());

    mockMvc.perform(MockMvcRequestBuilders.get("/customers/id-number/10000")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

  }

  @Test
  public void testFindByIdNumberError() throws Exception {

    Mockito.when(customerService.findByIdNumber(Mockito.anyString()))
        .thenThrow(RuntimeException.class);

    mockMvc.perform(MockMvcRequestBuilders.get("/customers/id-number/10000")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isInternalServerError());

  }

  // todo add find by email tests
  // why are we adding the throws exception
  @Test
  public void testFindByEmaillSuccess() throws Exception{

    Mockito.when(customerService.findByEmail(Mockito.anyString()))
        .thenReturn(new CustomerDto());

    mockMvc.perform(MockMvcRequestBuilders.get("/customers/email/tshepo@gmail.com")
    .contentType(MediaType.APPLICATION_JSON))
.andExpect(status().isOk());

  }

  @Test
  public void testFindEmailError() throws Exception {

    Mockito.when(customerService.findByEmail(Mockito.anyString()))
        .thenThrow(RuntimeException.class);

    mockMvc.perform(MockMvcRequestBuilders.get("/customers/email/tshepo@gmail.com")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isInternalServerError());

  }

  @Test
  public void testRegisterCustomer() throws Exception {

    Mockito.when(customerService.registerCustomer(Mockito.any(CustomerDto.class)))
        .thenReturn(new CustomerDto());

    String json = objectMapper.writeValueAsString(new CustomerDto());

    mockMvc.perform(MockMvcRequestBuilders.post("/customers/add")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());

  }

  // todo testRegisterCustomer error testing
  @Test
  public void testRegisterCustomerError() throws Exception {

    Mockito.when(customerService.registerCustomer(Mockito.any(CustomerDto.class)))
        .thenThrow(RuntimeException.class);

    String json = objectMapper.writeValueAsString(new CustomerDto());

    mockMvc.perform(MockMvcRequestBuilders.post("/customers/add")
            .content(json)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isInternalServerError());

  }
}