package com.netroutines.lms.exception;

import com.netroutines.lms.api.advice.exception.ApiException;
import org.springframework.http.HttpStatus;

public class PublisherNotFoundException extends ApiException {

    public PublisherNotFoundException() {
        super("PUBLISHER_NOT_FOUND", HttpStatus.NOT_FOUND);
    }

}
