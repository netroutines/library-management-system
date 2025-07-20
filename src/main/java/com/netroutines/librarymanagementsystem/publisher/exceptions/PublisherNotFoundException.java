package com.netroutines.librarymanagementsystem.publisher.exceptions;

import com.netroutines.librarymanagementsystem.shared.exceptions.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PublisherNotFoundException extends ApiException {
    public PublisherNotFoundException() {
        super("PUBLISHER_NOT_FOUND", HttpStatus.NOT_FOUND);
    }
}
