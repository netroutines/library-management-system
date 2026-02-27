package com.netroutines.lms.author.exception;

import com.netroutines.lms.api.advice.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class AuthorNotFoundException extends ApiException {

    public AuthorNotFoundException() {
        super("AUTHOR_NOT_FOUND", HttpStatus.NOT_FOUND);
    }

}
