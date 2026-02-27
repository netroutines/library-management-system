package com.netroutines.lms.publisher;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "publishers", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @GetMapping
    public ResponseEntity<List<PublisherResponse>> list() {
        return ResponseEntity.ok(publisherService.list());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PublisherResponse> create(@Valid @RequestBody PublisherRequest publisherRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(publisherService.create(publisherRequest));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PublisherResponse> read(@PathVariable Long id) {
        return ResponseEntity.ok(publisherService.read(id));
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PublisherResponse> update(@PathVariable Long id, @Valid @RequestBody PublisherRequest publisherRequest) {
        return ResponseEntity.ok(publisherService.update(id, publisherRequest));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        publisherService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
