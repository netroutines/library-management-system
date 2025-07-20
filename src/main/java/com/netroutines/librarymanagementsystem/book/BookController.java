package com.netroutines.librarymanagementsystem.book;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "books", produces = "application/json")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<Page<BookDTO>> list(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(bookService.list(page, size));
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<BookDTO> create(@Valid @RequestBody BookRequest bookRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(bookRequest));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BookDTO> read(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.read(id));
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<BookDTO> update(@PathVariable Long id, @Valid @RequestBody BookRequest bookRequest) {
        return ResponseEntity.ok(bookService.update(id, bookRequest));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
