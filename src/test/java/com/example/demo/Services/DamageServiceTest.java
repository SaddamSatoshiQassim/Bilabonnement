package com.example.demo.Services;

import com.example.demo.Models.DamageLine;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DamageServiceTest {

    @Test
    void shouldReturnTotalPriceForOneDamage() {
        List<DamageLine> damageLines = List.of(
                new DamageLine(1, 1, "Skramme på dør", new BigDecimal("1500.00"))
        );

        BigDecimal expectedTotalPrice = new BigDecimal("1500.00");

        DamageService damageService = new DamageService(null, null, null, null);

        BigDecimal actualTotalPrice =
                damageService.calculateTotalDamagePrice(damageLines);

        assertEquals(0, expectedTotalPrice.compareTo(actualTotalPrice));
    }

    @Test
    void shouldReturnSumOfMultipleDamageLines() {
        List<DamageLine> damageLines = List.of(
                new DamageLine(1, 1, "Skramme på dør", new BigDecimal("1500.00")),
                new DamageLine(2, 1, "Ødelagt rude", new BigDecimal("2000.00")),
                new DamageLine(3, 1, "Bule på bil", new BigDecimal("500.00"))
        );

        BigDecimal expectedTotalPrice = new BigDecimal("4000.00");

        DamageService damageService = new DamageService(null, null, null, null);

        BigDecimal actualTotalPrice =
                damageService.calculateTotalDamagePrice(damageLines);

        assertEquals(0, expectedTotalPrice.compareTo(actualTotalPrice));
    }

    @Test
    void shouldReturnZeroWhenNoDamageLinesExist() {
        List<DamageLine> damageLines = List.of();

        BigDecimal expectedTotalPrice = BigDecimal.ZERO;

        DamageService damageService = new DamageService(null, null, null, null);

        BigDecimal actualTotalPrice =
                damageService.calculateTotalDamagePrice(damageLines);

        assertEquals(0, expectedTotalPrice.compareTo(actualTotalPrice));
    }
}