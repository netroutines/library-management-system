package com.netroutines.librarymanagementsystem.genre.exceptions;

import com.netroutines.librarymanagementsystem.shared.exceptions.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GenreNotFoundException extends ApiException {
    public GenreNotFoundException() {
        super("GENRE_NOT_FOUND", HttpStatus.NOT_FOUND);
    }
}
