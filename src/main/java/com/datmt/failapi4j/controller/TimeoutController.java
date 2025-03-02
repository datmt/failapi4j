package com.datmt.failapi4j.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/timeout")
@Tag(name = "Timeout Controller", description = "Endpoints for simulating API timeouts")
public class TimeoutController {

    @Operation(summary = "Simulate GET timeout",
            description = "Delays the response for the specified duration in milliseconds")
    @GetMapping("/{timeoutMs}")
    public ResponseEntity<String> getWithTimeout(
            @Parameter(description = "Timeout duration in milliseconds", example = "2000")
            @PathVariable long timeoutMs) throws InterruptedException {
        Thread.sleep(timeoutMs);
        return ResponseEntity.ok("GET request completed after " + timeoutMs + "ms delay");
    }

    @Operation(summary = "Simulate POST timeout",
            description = "Delays the response for the specified duration in milliseconds")
    @PostMapping("/{timeoutMs}")
    public ResponseEntity<String> postWithTimeout(
            @Parameter(description = "Timeout duration in milliseconds", example = "2000")
            @PathVariable long timeoutMs,
            @Parameter(description = "Optional request body")
            @RequestBody(required = false) String body) throws InterruptedException {
        Thread.sleep(timeoutMs);
        return ResponseEntity.ok("POST request completed after " + timeoutMs + "ms delay");
    }

    @Operation(summary = "Simulate PUT timeout",
            description = "Delays the response for the specified duration in milliseconds")
    @PutMapping("/{timeoutMs}")
    public ResponseEntity<String> putWithTimeout(
            @Parameter(description = "Timeout duration in milliseconds", example = "2000")
            @PathVariable long timeoutMs,
            @Parameter(description = "Optional request body")
            @RequestBody(required = false) String body) throws InterruptedException {
        Thread.sleep(timeoutMs);
        return ResponseEntity.ok("PUT request completed after " + timeoutMs + "ms delay");
    }

    @Operation(summary = "Simulate DELETE timeout",
            description = "Delays the response for the specified duration in milliseconds")
    @DeleteMapping("/{timeoutMs}")
    public ResponseEntity<String> deleteWithTimeout(
            @Parameter(description = "Timeout duration in milliseconds", example = "2000")
            @PathVariable long timeoutMs) throws InterruptedException {
        Thread.sleep(timeoutMs);
        return ResponseEntity.ok("DELETE request completed after " + timeoutMs + "ms delay");
    }

    @Operation(summary = "Simulate PATCH timeout",
            description = "Delays the response for the specified duration in milliseconds")
    @PatchMapping("/{timeoutMs}")
    public ResponseEntity<String> patchWithTimeout(
            @Parameter(description = "Timeout duration in milliseconds", example = "2000")
            @PathVariable long timeoutMs,
            @Parameter(description = "Optional request body")
            @RequestBody(required = false) String body) throws InterruptedException {
        Thread.sleep(timeoutMs);
        return ResponseEntity.ok("PATCH request completed after " + timeoutMs + "ms delay");
    }
}
