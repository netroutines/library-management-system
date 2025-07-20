package com.netroutines.librarymanagementsystem.shared.dto;

import java.util.Map;

public record FieldErrorDTO(Integer status, String message, Map<String, String> errors) {
}
