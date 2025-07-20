package com.netroutines.librarymanagementsystem.author.exceptions;

import com.netroutines.librarymanagementsystem.shared.exceptions.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AuthorNotFoundException extends ApiException {
    public AuthorNotFoundException() {
        super("AUTHOR_NOT_FOUND", HttpStatus.NOT_FOUND);
    }
}
