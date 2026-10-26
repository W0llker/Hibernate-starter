package org.example.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record Registered(LocalDate registrationDate) {
    public long getYear() {
        return ChronoUnit.YEARS.between(registrationDate, LocalDate.now());
    }
}
