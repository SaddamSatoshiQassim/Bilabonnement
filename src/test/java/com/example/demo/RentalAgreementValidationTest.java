package com.example.demo;

import com.example.demo.Models.RentalAgreement;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RentalAgreementValidationTest {

    @Test
    void shouldAcceptValidStartAndEndDate() {
        // Arrange
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(7);

        RentalAgreement rentalAgreement = new RentalAgreement(startDate, endDate);

        // Act
        boolean isValid = rentalAgreement.hasValidDates();

        // Assert
        assertTrue(isValid);
    }

    @Test
    void shouldRejectNullStartDate() {
        // Arrange
        LocalDate startDate = null;
        LocalDate endDate = LocalDate.now().plusDays(7);

        RentalAgreement rentalAgreement = new RentalAgreement(startDate, endDate);

        // Act
        boolean isValid = rentalAgreement.hasValidDates();

        // Assert
        assertFalse(isValid);
    }

    @Test
    void shouldRejectNullEndDate() {
        // Arrange
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = null;

        RentalAgreement rentalAgreement = new RentalAgreement(startDate, endDate);

        // Act
        boolean isValid = rentalAgreement.hasValidDates();

        // Assert
        assertFalse(isValid);
    }

    @Test
    void shouldRejectEndDateBeforeStartDate() {
        // Arrange
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.minusDays(1);

        RentalAgreement rentalAgreement = new RentalAgreement(startDate, endDate);

        // Act
        boolean isValid = rentalAgreement.hasValidDates();

        // Assert
        assertFalse(isValid);
    }
}

