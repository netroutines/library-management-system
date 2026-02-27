package com.netroutines.lms.publisher.exception;

import com.netroutines.lms.api.advice.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class PublisherNotFoundException extends ApiException {

    public PublisherNotFoundException() {
        super("PUBLISHER_NOT_FOUND", HttpStatus.NOT_FOUND);
    }

}
