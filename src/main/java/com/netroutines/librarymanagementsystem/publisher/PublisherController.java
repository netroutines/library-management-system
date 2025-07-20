package com.netroutines.librarymanagementsystem.publisher;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "publishers", produces = "application/json")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @GetMapping
    public ResponseEntity<List<PublisherDTO>> list() {
        return ResponseEntity.ok(publisherService.list());
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<PublisherDTO> create(@Valid @RequestBody PublisherRequest publisherRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(publisherService.create(publisherRequest));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PublisherDTO> read(@PathVariable Long id) {
        return ResponseEntity.ok(publisherService.read(id));
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<PublisherDTO> update(@PathVariable Long id, @Valid @RequestBody PublisherRequest publisherRequest) {
        return ResponseEntity.ok(publisherService.update(id, publisherRequest));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        publisherService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
