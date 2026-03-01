package com.netroutines.lms.api.advice.response;

import java.time.Instant;

public record ErrorResponse(
        Integer status,
        String error,
        String message,
        String path,
        Instant timestamp
) {
}
