package com.maximedyma.tennis.service;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class PlayerAlreadyExistsException extends RuntimeException {
    public PlayerAlreadyExistsException(String firstName, String lastName, LocalDate birthDate) {
        super("Player with firstName " + firstName + " lastName " + lastName + " and birthDate " + birthDate + " already exists.");
    }
}
