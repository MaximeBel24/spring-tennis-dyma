package com.maximedyma.tennis.service;

import jakarta.validation.constraints.NotBlank;

public class PlayerAlreadyExistsException extends RuntimeException {
    public PlayerAlreadyExistsException(String lastName) {
        super("Player with last name " + lastName + " already exists.");
    }
}
