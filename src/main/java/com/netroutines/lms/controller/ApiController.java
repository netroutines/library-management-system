package com.netroutines.lms.controller;

import com.netroutines.lms.config.property.AppProperties;
import com.netroutines.lms.controller.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final AppProperties appProperties;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> index() {
        return ResponseEntity.ok(new ApiResponse(appProperties.name(), Instant.now()));
    }

}
