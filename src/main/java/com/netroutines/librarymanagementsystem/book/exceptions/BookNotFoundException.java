package com.netroutines.librarymanagementsystem.book.exceptions;

import com.netroutines.librarymanagementsystem.shared.exceptions.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotFoundException extends ApiException {
    public BookNotFoundException() {
        super("BOOK_NOT_FOUND", HttpStatus.NOT_FOUND);
    }
}
