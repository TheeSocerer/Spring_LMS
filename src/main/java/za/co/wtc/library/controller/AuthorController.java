package za.co.wtc.library.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import za.co.wtc.library.dto.AuthorDTO;
import za.co.wtc.library.dto.CustomerDto;
import za.co.wtc.library.service.AuthorService;


@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @RequestMapping(method = RequestMethod.GET, value = "email/{email}", produces = {
        "application/json"})
    public ResponseEntity<AuthorDTO> findByEmail(@PathVariable("email") String email){
        AuthorDTO authorDTO = authorService.findAuthorByEmail(email);

        return new ResponseEntity<>(authorDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add", consumes = {
        "application/json"}, produces = {"application/json"})
    public ResponseEntity<AuthorDTO> registerAuthor(@RequestBody AuthorDTO authorDTO){
        AuthorDTO reigistereAuthorDTO = authorService.registerAuthor(authorDTO);

        return new ResponseEntity<>(reigistereAuthorDTO, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/edit", produces = {"application/json"})
    public ResponseEntity<AuthorDTO> editAuthorDetails(@RequestBody AuthorDTO authorDTO) {
        AuthorDTO editedAuthor = authorService.editAuthor(authorDTO);
        return new ResponseEntity<>(editedAuthor, HttpStatus.OK);
    }
}

