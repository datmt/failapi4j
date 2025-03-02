package com.datmt.failapi4j.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/error")
@Tag(name = "HTTP Error Controller", description = "Endpoints for simulating HTTP error responses")
public class HttpErrorController {

    @Operation(summary = "Simulate GET error",
            description = "Returns an error response with the specified HTTP status code")
    @GetMapping("/{statusCode}")
    public ResponseEntity<Map<String, Object>> getWithError(
            @Parameter(description = "HTTP status code to return", example = "404")
            @PathVariable int statusCode) {
        Map<String, Object> response = createErrorResponse(statusCode);
        return ResponseEntity.status(statusCode).body(response);
    }

    @Operation(summary = "Simulate POST error",
            description = "Returns an error response with the specified HTTP status code")
    @PostMapping("/{statusCode}")
    public ResponseEntity<Map<String, Object>> postWithError(
            @Parameter(description = "HTTP status code to return", example = "400")
            @PathVariable int statusCode,
            @Parameter(description = "Optional request body")
            @RequestBody(required = false) String body) {
        Map<String, Object> response = createErrorResponse(statusCode);
        return ResponseEntity.status(statusCode).body(response);
    }

    @Operation(summary = "Simulate PUT error",
            description = "Returns an error response with the specified HTTP status code")
    @PutMapping("/{statusCode}")
    public ResponseEntity<Map<String, Object>> putWithError(
            @Parameter(description = "HTTP status code to return", example = "403")
            @PathVariable int statusCode,
            @Parameter(description = "Optional request body")
            @RequestBody(required = false) String body) {
        Map<String, Object> response = createErrorResponse(statusCode);
        return ResponseEntity.status(statusCode).body(response);
    }

    @Operation(summary = "Simulate DELETE error",
            description = "Returns an error response with the specified HTTP status code")
    @DeleteMapping("/{statusCode}")
    public ResponseEntity<Map<String, Object>> deleteWithError(
            @Parameter(description = "HTTP status code to return", example = "404")
            @PathVariable int statusCode) {
        Map<String, Object> response = createErrorResponse(statusCode);
        return ResponseEntity.status(statusCode).body(response);
    }

    @Operation(summary = "Simulate PATCH error",
            description = "Returns an error response with the specified HTTP status code")
    @PatchMapping("/{statusCode}")
    public ResponseEntity<Map<String, Object>> patchWithError(
            @Parameter(description = "HTTP status code to return", example = "500")
            @PathVariable int statusCode,
            @Parameter(description = "Optional request body")
            @RequestBody(required = false) String body) {
        Map<String, Object> response = createErrorResponse(statusCode);
        return ResponseEntity.status(statusCode).body(response);
    }

    private Map<String, Object> createErrorResponse(int statusCode) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", statusCode);
        response.put("error", getErrorMessageForStatus(statusCode));
        response.put("timestamp", System.currentTimeMillis());
        return response;
    }

    private String getErrorMessageForStatus(int statusCode) {
        return switch (statusCode) {
            case 400 -> "Bad Request";
            case 401 -> "Unauthorized";
            case 403 -> "Forbidden";
            case 404 -> "Not Found";
            case 405 -> "Method Not Allowed";
            case 406 -> "Not Acceptable";
            case 408 -> "Request Timeout";
            case 409 -> "Conflict";
            case 410 -> "Gone";
            case 415 -> "Unsupported Media Type";
            case 429 -> "Too Many Requests";
            case 500 -> "Internal Server Error";
            case 501 -> "Not Implemented";
            case 502 -> "Bad Gateway";
            case 503 -> "Service Unavailable";
            case 504 -> "Gateway Timeout";
            default -> "Unknown Error";
        };
    }
}
