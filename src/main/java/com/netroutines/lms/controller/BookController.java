package com.netroutines.lms.controller;

import com.netroutines.lms.controller.request.BookRequest;
import com.netroutines.lms.controller.response.BookResponse;
import com.netroutines.lms.service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = "books", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<Page<BookResponse>> list(
            @RequestParam(required = false, defaultValue = "0") @Min(0) int page,
            @RequestParam(required = false, defaultValue = "10") @Min(1) @Max(100) int size
    ) {
        return ResponseEntity.ok(bookService.list(page, size));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookResponse> create(@Valid @RequestBody BookRequest bookRequest) {
        var response = bookService.create(bookRequest);
        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BookResponse> read(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(bookService.read(id));
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookResponse> update(@PathVariable @Min(1) Long id, @Valid @RequestBody BookRequest bookRequest) {
        return ResponseEntity.ok(bookService.update(id, bookRequest));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Min(1) Long id) {
        bookService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
