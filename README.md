# failapi4j

A Spring Boot API designed to simulate various API failure scenarios for testing and development purposes. This tool helps developers test how their applications handle different types of API failures, including timeouts, specific HTTP errors, and random failures with configurable probabilities.

## Features

### 1. Timeout Simulation
Endpoint: `/api/timeout/{timeoutMs}`
- Simulates API timeouts with specified delay
- Supports all HTTP methods (GET, POST, PUT, DELETE, PATCH)
- Parameter:
  - `timeoutMs`: Delay duration in milliseconds

Example:
```bash
# Simulate 2-second timeout
curl http://localhost:8080/api/timeout/2000
```

### 2. HTTP Error Simulation
Endpoint: `/api/error/{statusCode}`
- Returns specified HTTP error responses
- Supports all HTTP methods
- Parameter:
  - `statusCode`: HTTP status code to return (e.g., 400, 404, 500)
- Returns JSON with status, error message, and timestamp

Example:
```bash
# Simulate 404 Not Found error
curl http://localhost:8080/api/error/404
```

### 3. Random Error Simulation
Endpoint: `/api/random/{errorPercent}/{delayMs}/{statusCode}`
- Simulates errors with configurable probability and delay
- Supports all HTTP methods
- Parameters:
  - `errorPercent`: Probability of error (0-100)
  - `delayMs`: Delay in milliseconds
  - `statusCode`: HTTP status code for error responses
- Success Response: `{"content": "success"}`
- Error Response: JSON with status, error message, and timestamp

Example:
```bash
# 50% chance of 404 error with 1-second delay
curl http://localhost:8080/api/random/50/1000/404
```

### API Documentation
The API is documented using OpenAPI (Swagger). You can access the interactive API documentation at:
```
http://localhost:8080/swagger-ui.html
```

This provides:
- Interactive API documentation
- Request/response examples
- Try-it-out functionality
- Detailed parameter descriptions
- Response schemas

## Requirements
- Java 21
- Spring Boot 3.4.3
- Maven

## Getting Started

1. Clone the repository:
```bash
git clone https://github.com/yourusername/failapi4j.git
cd failapi4j
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

## Docker Support

### Building the Docker Image
```bash
docker build -t failapi4j .
```

### Running the Container
```bash
docker run -d -p 8080:8080 --name failapi4j failapi4j
```

### Environment Variables
- `JAVA_OPTS`: JVM options (default: "-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0")

Example with custom Java options:
```bash
docker run -d -p 8080:8080 -e JAVA_OPTS="-Xmx512m" --name failapi4j failapi4j
```

## Common HTTP Status Codes

The API supports all standard HTTP status codes. Here are some commonly used ones:

- 400: Bad Request
- 401: Unauthorized
- 403: Forbidden
- 404: Not Found
- 408: Request Timeout
- 429: Too Many Requests
- 500: Internal Server Error
- 503: Service Unavailable
- 504: Gateway Timeout

## Use Cases

1. Testing error handling in client applications
2. Simulating unreliable network conditions
3. Load testing with variable response times
4. Testing retry mechanisms
5. Validating error handling UI/UX
6. Testing circuit breakers and fallback mechanisms

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.
