package com.netroutines.lms.controller;

import com.netroutines.lms.controller.request.PublisherRequest;
import com.netroutines.lms.controller.response.PublisherResponse;
import com.netroutines.lms.service.PublisherService;
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
@RequestMapping(path = "publishers", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @GetMapping
    public ResponseEntity<List<PublisherResponse>> list() {
        return ResponseEntity.ok(publisherService.list());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PublisherResponse> create(@Valid @RequestBody PublisherRequest publisherRequest) {
        var response = publisherService.create(publisherRequest);
        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PublisherResponse> read(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(publisherService.read(id));
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PublisherResponse> update(@PathVariable @Min(1) Long id, @Valid @RequestBody PublisherRequest publisherRequest) {
        return ResponseEntity.ok(publisherService.update(id, publisherRequest));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Min(1) Long id) {
        publisherService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
