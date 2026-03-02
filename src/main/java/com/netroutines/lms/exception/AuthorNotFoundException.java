package com.netroutines.lms.exception;

import com.netroutines.lms.api.advice.exception.ApiException;
import org.springframework.http.HttpStatus;

public class AuthorNotFoundException extends ApiException {

    public AuthorNotFoundException() {
        super("AUTHOR_NOT_FOUND", HttpStatus.NOT_FOUND);
    }

}
