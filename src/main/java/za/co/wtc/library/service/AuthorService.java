package za.co.wtc.library.service;

import za.co.wtc.library.dto.AuthorDTO;

// todo implement this and also the controller and related test for all classes
public interface AuthorService {

  AuthorDTO findAuthorByEmail(String email);

  AuthorDTO registerAuthor(AuthorDTO author);

  AuthorDTO editAuthor(AuthorDTO author);

}
