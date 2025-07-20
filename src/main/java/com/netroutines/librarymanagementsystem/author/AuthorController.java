package com.netroutines.librarymanagementsystem.author;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "authors", produces = "application/json")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> list() {
        return ResponseEntity.ok(authorService.list());
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<AuthorDTO> create(@Valid @RequestBody AuthorRequest authorRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.create(authorRequest));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AuthorDTO> read(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.read(id));
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<AuthorDTO> update(@PathVariable Long id, @Valid @RequestBody AuthorRequest authorRequest) {
        return ResponseEntity.ok(authorService.update(id, authorRequest));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        authorService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
