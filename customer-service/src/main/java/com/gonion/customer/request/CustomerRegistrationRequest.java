package com.gonion.customer.request;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email
) {
}
