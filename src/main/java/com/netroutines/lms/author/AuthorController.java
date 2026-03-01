package com.netroutines.lms.author;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(path = "authors", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorResponse>> list() {
        return ResponseEntity.ok(authorService.list());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorResponse> create(@Valid @RequestBody AuthorRequest authorRequest) {
        var response = authorService.create(authorRequest);
        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AuthorResponse> read(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(authorService.read(id));
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthorResponse> update(@PathVariable @Min(1) Long id, @Valid @RequestBody AuthorRequest authorRequest) {
        return ResponseEntity.ok(authorService.update(id, authorRequest));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Min(1) Long id) {
        authorService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
