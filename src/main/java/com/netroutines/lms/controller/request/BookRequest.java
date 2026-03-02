package com.netroutines.lms.controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record BookRequest(
        @NotNull @NotEmpty @Size(max = 100) String title,
        @NotNull @Positive Long genreId,
        @NotNull @Positive Long publisherId,
        @NotEmpty Set<@Positive Long> authorIds
) {
}
