package com.netroutines.lms.exception;

import com.netroutines.lms.api.advice.exception.ApiException;
import org.springframework.http.HttpStatus;

public class GenreNotFoundException extends ApiException {

    public GenreNotFoundException() {
        super("GENRE_NOT_FOUND", HttpStatus.NOT_FOUND);
    }

}
