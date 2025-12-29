package com.starttohkar.dto;

public class StripeResponse {
    private final String status;
    private final String message;
    private final String sessionId;
    private final String sessionUrl;

    // Private constructor (used only by the builder)
    private StripeResponse(Builder builder) {
        this.status = builder.status;
        this.message = builder.message;
        this.sessionId = builder.sessionId;
        this.sessionUrl = builder.sessionUrl;
    }

    // Getters (immutable class - no setters)
    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getSessionUrl() {
        return sessionUrl;
    }

    // Static factory method to get the builder
    public static Builder builder() {
        return new Builder();
    }

    // Static nested Builder class
    public static class Builder {
        private String status;
        private String message;
        private String sessionId;
        private String sessionUrl;

        // Private constructor
        private Builder() {}

        // Fluent setters (return this for chaining)
        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder sessionId(String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public Builder sessionUrl(String sessionUrl) {
            this.sessionUrl = sessionUrl;
            return this;
        }

        // Build method (creates the final object)
        public StripeResponse build() {
            // Optional: Add validation here
            if (status == null) {
                throw new IllegalArgumentException("status cannot be null");
            }
            return new StripeResponse(this);
        }
    }

    // Optional: toString for easy debugging
    @Override
    public String toString() {
        return "StripeResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", sessionUrl='" + sessionUrl + '\'' +
                '}';
    }
}