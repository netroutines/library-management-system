package com.netroutines.lms.controller.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record GenreRequest(@NotNull @NotEmpty @Size(max = 100) String name) {
}
