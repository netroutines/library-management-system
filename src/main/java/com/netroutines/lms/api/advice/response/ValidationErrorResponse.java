package com.netroutines.lms.api.advice.response;

import java.time.Instant;
import java.util.Map;

public record ValidationErrorResponse(
        Integer status,
        String error,
        String message,
        Map<String, String> errors,
        String path,
        Instant timestamp
) {
}
