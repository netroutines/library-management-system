package com.netroutines.librarymanagementsystem.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ApiController {

    @GetMapping
    public ResponseEntity<ApiDTO> index() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiDTO(HttpStatus.OK.value(), "library-management-system"));
    }
}
