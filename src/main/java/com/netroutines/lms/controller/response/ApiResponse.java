package com.netroutines.lms.controller.response;

import java.time.Instant;

public record ApiResponse(String appName, Instant timestamp) {
}
