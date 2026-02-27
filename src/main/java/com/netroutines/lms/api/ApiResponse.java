package com.netroutines.lms.api;

import java.time.Instant;

public record ApiResponse(String appName, Instant timestamp) {
}
