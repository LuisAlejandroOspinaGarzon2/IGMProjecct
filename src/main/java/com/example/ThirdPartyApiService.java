package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpStatus;

@Service
public class ThirdPartyApiService {

    private final RestTemplate restTemplate;
    private final String thirdPartyApiUrl = "https://example.com/api"; // Placeholder URL

    @Autowired
    public ThirdPartyApiService() {
        this.restTemplate = new RestTemplate();
    }

    public String getDataFromThirdPartyApi() {
        // Simulate rate limiting (return 429 status code occasionally)
        if (Math.random() < 0.2) {
            throw new RateLimitException("Rate limit exceeded", new HttpClientErrorException(HttpStatus.TOO_MANY_REQUESTS));
        }

        // Simulate making a request to the third-party API (replace with your logic)
        try {
            String response = restTemplate.getForObject(thirdPartyApiUrl, String.class);
            return response;
        } catch (Exception e) {
            // Implement retry mechanism here (explained in the next section)
            return retryRequest();
        }
    }

    private String retryRequest() {
        // Implement a retry mechanism (e.g., exponential backoff)
        // In a real-world scenario, you would implement a more robust retry strategy.
        // For simplicity, we'll retry immediately.
        try {
            Thread.sleep(1000); // Wait for 1 second before retrying
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return getDataFromThirdPartyApi(); // Retry the request
    }

    // Define custom exception for rate limiting
    private static class RateLimitException extends RuntimeException {
        private final HttpClientErrorException statusCode;

        public RateLimitException(String message, HttpClientErrorException statusCode) {
            super(message);
            this.statusCode = statusCode;
        }

        public HttpClientErrorException getStatusCode() {
            return statusCode;
        }
    }
}