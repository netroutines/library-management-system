package com.netroutines.lms.controller;

import com.netroutines.lms.controller.request.GenreRequest;
import com.netroutines.lms.controller.response.GenreResponse;
import com.netroutines.lms.service.GenreService;
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
@RequestMapping(path = "genres", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreResponse>> list() {
        return ResponseEntity.ok(genreService.list());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenreResponse> create(@Valid @RequestBody GenreRequest genreRequest) {
        var response = genreService.create(genreRequest);
        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<GenreResponse> read(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(genreService.read(id));
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenreResponse> update(@PathVariable @Min(1) Long id, @Valid @RequestBody GenreRequest genreRequest) {
        return ResponseEntity.ok(genreService.update(id, genreRequest));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Min(1) Long id) {
        genreService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
