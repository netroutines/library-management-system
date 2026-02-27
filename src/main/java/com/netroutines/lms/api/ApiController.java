package com.netroutines.lms.api;

import com.netroutines.lms.config.properties.AppProperties;
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
