package com.netroutines.lms.api.advice.responses;

import java.util.Map;

public record ValidationErrorResponse(Integer status, String message, Map<String, String> errors) {
}
