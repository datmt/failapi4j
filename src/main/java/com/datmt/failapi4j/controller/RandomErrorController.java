package com.datmt.failapi4j.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/random")
@Tag(name = "Random Error Controller", description = "Endpoints for simulating random errors with configurable probability")
public class RandomErrorController {
    private final Random random = new Random();

    @Operation(summary = "Simulate GET with random error",
            description = "Returns either a success response or an error based on the specified probability")
    @GetMapping("/{errorPercent}/{delayMs}/{statusCode}")
    public ResponseEntity<Map<String, Object>> getWithRandomError(
            @Parameter(description = "Probability of error (0-100)", example = "50")
            @PathVariable int errorPercent,
            @Parameter(description = "Delay in milliseconds", example = "1000")
            @PathVariable long delayMs,
            @Parameter(description = "HTTP status code to return on error", example = "500")
            @PathVariable int statusCode) throws InterruptedException {
        return processRequest(errorPercent, delayMs, statusCode);
    }

    @Operation(summary = "Simulate POST with random error",
            description = "Returns either a success response or an error based on the specified probability")
    @PostMapping("/{errorPercent}/{delayMs}/{statusCode}")
    public ResponseEntity<Map<String, Object>> postWithRandomError(
            @Parameter(description = "Probability of error (0-100)", example = "50")
            @PathVariable int errorPercent,
            @Parameter(description = "Delay in milliseconds", example = "1000")
            @PathVariable long delayMs,
            @Parameter(description = "HTTP status code to return on error", example = "500")
            @PathVariable int statusCode,
            @Parameter(description = "Optional request body")
            @RequestBody(required = false) String body) throws InterruptedException {
        return processRequest(errorPercent, delayMs, statusCode);
    }

    @Operation(summary = "Simulate PUT with random error",
            description = "Returns either a success response or an error based on the specified probability")
    @PutMapping("/{errorPercent}/{delayMs}/{statusCode}")
    public ResponseEntity<Map<String, Object>> putWithRandomError(
            @Parameter(description = "Probability of error (0-100)", example = "50")
            @PathVariable int errorPercent,
            @Parameter(description = "Delay in milliseconds", example = "1000")
            @PathVariable long delayMs,
            @Parameter(description = "HTTP status code to return on error", example = "500")
            @PathVariable int statusCode,
            @Parameter(description = "Optional request body")
            @RequestBody(required = false) String body) throws InterruptedException {
        return processRequest(errorPercent, delayMs, statusCode);
    }

    @Operation(summary = "Simulate DELETE with random error",
            description = "Returns either a success response or an error based on the specified probability")
    @DeleteMapping("/{errorPercent}/{delayMs}/{statusCode}")
    public ResponseEntity<Map<String, Object>> deleteWithRandomError(
            @Parameter(description = "Probability of error (0-100)", example = "50")
            @PathVariable int errorPercent,
            @Parameter(description = "Delay in milliseconds", example = "1000")
            @PathVariable long delayMs,
            @Parameter(description = "HTTP status code to return on error", example = "500")
            @PathVariable int statusCode) throws InterruptedException {
        return processRequest(errorPercent, delayMs, statusCode);
    }

    @Operation(summary = "Simulate PATCH with random error",
            description = "Returns either a success response or an error based on the specified probability")
    @PatchMapping("/{errorPercent}/{delayMs}/{statusCode}")
    public ResponseEntity<Map<String, Object>> patchWithRandomError(
            @Parameter(description = "Probability of error (0-100)", example = "50")
            @PathVariable int errorPercent,
            @Parameter(description = "Delay in milliseconds", example = "1000")
            @PathVariable long delayMs,
            @Parameter(description = "HTTP status code to return on error", example = "500")
            @PathVariable int statusCode,
            @Parameter(description = "Optional request body")
            @RequestBody(required = false) String body) throws InterruptedException {
        return processRequest(errorPercent, delayMs, statusCode);
    }

    private ResponseEntity<Map<String, Object>> processRequest(int errorPercent, long delayMs, int statusCode) 
            throws InterruptedException {
        // Validate error percentage
        if (errorPercent < 0 || errorPercent > 100) {
            Map<String, Object> validationError = new HashMap<>();
            validationError.put("error", "Error percentage must be between 0 and 100");
            return ResponseEntity.badRequest().body(validationError);
        }

        // Apply delay
        if (delayMs > 0) {
            Thread.sleep(delayMs);
        }

        // Generate random number between 1 and 100
        int randomValue = random.nextInt(100) + 1;

        // If random value is less than or equal to error percentage, return error
        if (randomValue <= errorPercent) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", statusCode);
            errorResponse.put("error", getErrorMessageForStatus(statusCode));
            errorResponse.put("timestamp", System.currentTimeMillis());
            return ResponseEntity.status(statusCode).body(errorResponse);
        }

        // Return success response
        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put("content", "success");
        return ResponseEntity.ok(successResponse);
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
