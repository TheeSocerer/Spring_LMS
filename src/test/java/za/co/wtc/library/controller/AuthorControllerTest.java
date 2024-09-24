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
import za.co.wtc.library.dto.AuthorDTO;
import za.co.wtc.library.dto.CustomerDto;
import za.co.wtc.library.exception.LibraryExceptionHandler;
import za.co.wtc.library.service.AuthorService;
import za.co.wtc.library.service.CustomerService;

@WebMvcTest(AuthorController.class)
@ContextConfiguration(classes = {AuthorController.class, LibraryExceptionHandler.class})
public class AuthorControllerTest {
    @MockBean
    private AuthorService authorService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testFindByEmaillSuccess() throws Exception{

        Mockito.when(authorService.findAuthorByEmail(Mockito.anyString()))
                .thenReturn(new AuthorDTO());

        mockMvc.perform(MockMvcRequestBuilders.get("/author/email/tshepo@example.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void testFindByEmailError() throws Exception{
        Mockito.when(authorService.findAuthorByEmail(Mockito.anyString()))
                .thenThrow(RuntimeException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/author/email/tshepo@example.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }


}
