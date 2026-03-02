package com.netroutines.lms.exception;

import com.netroutines.lms.api.advice.exception.ApiException;
import org.springframework.http.HttpStatus;

public class BookNotFoundException extends ApiException {

    public BookNotFoundException() {
        super("BOOK_NOT_FOUND", HttpStatus.NOT_FOUND);
    }

}
