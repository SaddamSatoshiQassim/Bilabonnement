package com.example.demo;

import com.example.demo.Models.DamageLine;
import com.example.demo.Services.DamageService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DamageReportServiceTest {

    @Test
    void shouldReturnTotalPriceForOneDamage() {
        // Arrange
        List<DamageLine> damageLines = List.of(
                new DamageLine(1, "Skramme på dør", 1500.00)
        );
        double expectedTotalPrice = 1500.00;

        DamageService damageService = new DamageService();

        // Act
        double actualTotalPrice = damageService.calculateTotalDamagePrice(damageLines);

        // Assert
        assertEquals(expectedTotalPrice, actualTotalPrice);
    }

    @Test
    void shouldReturnSumOfMultipleDamageLines() {
        // Arrange
        List<DamageLine> damageLines = List.of(
                new DamageLine(1, "Skramme på dør", 1500.00),
                new DamageLine(2, "Ødelagt rude", 2000.00),
                new DamageLine(3, "Bule på bil", 500.00)
        );
        double expectedTotalPrice = 4000.00;

        DamageService damageService = new DamageService();

        // Act
        double actualTotalPrice = damageService.calculateTotalDamagePrice(damageLines);

        // Assert
        assertEquals(expectedTotalPrice, actualTotalPrice);
    }

    @Test
    void shouldReturnZeroWhenNoDamageLinesExist() {
        // Arrange
        List<DamageLine> damageLines = List.of();
        double expectedTotalPrice = 0.00;

        DamageService damageService = new DamageService();

        // Act
        double actualTotalPrice = damageService.calculateTotalDamagePrice(damageLines);

        // Assert
        assertEquals(expectedTotalPrice, actualTotalPrice);
    }
}

