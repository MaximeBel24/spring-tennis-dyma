package com.maximedyma.tennis.service;

import java.time.LocalDate;

public class TournamentAlreadyExistsException extends RuntimeException {
    public TournamentAlreadyExistsException(String name) {
        super("Tournament with name " + name + " already exists.");
    }
}
