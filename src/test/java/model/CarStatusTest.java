package model;

import com.example.demo.Models.Car;
import com.example.demo.Models.CarStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarStatusTest {

    @Test
    void carShouldBecomeUnavailableWhenRentalIsCreated() {
        // Arrange
        Car car = new Car(
                1,
                "VIN113",
                "Toyota",
                "Yaris",
                CarStatus.AVAILABLE,
                BigDecimal.valueOf(500)
        );

        // Act
        car.markAsUnavailable();

        // Assert
        assertEquals(CarStatus.UNAVAILABLE, car.getStatus());
    }

    @Test
    void carShouldBecomeAvailableWhenReturnedWithoutDamage() {
        // Arrange
        Car car = new Car(
                1,
                "VIN123",
                "Toyota",
                "Yaris",
                CarStatus.UNAVAILABLE,
                BigDecimal.valueOf(500)
        );

        // Act
        car.markAsAvailable();

        // Assert
        assertEquals(CarStatus.AVAILABLE, car.getStatus());
    }

    @Test
    void carShouldBecomeDamagedWhenReturnedWithDamage() {
        // Arrange
        Car car = new Car(
                1,
                "VIN113",
                "Toyota",
                "Yaris",
                CarStatus.UNAVAILABLE,
                BigDecimal.valueOf(500)
        );

        // Act
        car.markAsDamaged();

        // Assert
        assertEquals(CarStatus.DAMAGED, car.getStatus());
    }
}
