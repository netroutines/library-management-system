package com.netroutines.lms.genre.exception;

import com.netroutines.lms.api.advice.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class GenreNotFoundException extends ApiException {

    public GenreNotFoundException() {
        super("GENRE_NOT_FOUND", HttpStatus.NOT_FOUND);
    }

}
