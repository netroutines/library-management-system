package com.netroutines.lms.genre;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "genres", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreResponse>> list() {
        return ResponseEntity.ok(genreService.list());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenreResponse> create(@Valid @RequestBody GenreRequest genreRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(genreService.create(genreRequest));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<GenreResponse> read(@PathVariable Long id) {
        return ResponseEntity.ok(genreService.read(id));
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenreResponse> update(@PathVariable Long id, @Valid @RequestBody GenreRequest genreRequest) {
        return ResponseEntity.ok(genreService.update(id, genreRequest));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        genreService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
