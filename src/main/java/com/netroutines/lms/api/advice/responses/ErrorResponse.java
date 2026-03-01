package com.netroutines.lms.api.advice.responses;

import java.time.Instant;

public record ErrorResponse(
        Integer status,
        String error,
        String message,
        String path,
        Instant timestamp
) {
}
