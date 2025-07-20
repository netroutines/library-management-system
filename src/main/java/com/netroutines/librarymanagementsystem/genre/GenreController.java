package com.netroutines.librarymanagementsystem.genre;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "genres", produces = "application/json")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreDTO>> list() {
        return ResponseEntity.ok(genreService.list());
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<GenreDTO> create(@Valid @RequestBody GenreRequest genreRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(genreService.create(genreRequest));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<GenreDTO> read(@PathVariable Long id) {
        return ResponseEntity.ok(genreService.read(id));
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<GenreDTO> update(@PathVariable Long id, @Valid @RequestBody GenreRequest genreRequest) {
        return ResponseEntity.ok(genreService.update(id, genreRequest));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        genreService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
