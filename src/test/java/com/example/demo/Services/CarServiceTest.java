package com.example.demo.Services;

import com.example.demo.Models.Car;
import com.example.demo.Models.CarStatus;
import com.example.demo.Repositories.CarRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CarServiceTest {

    @Test
    void shouldMarkCarAsRented() {
        FakeCarRepository fakeCarRepository = new FakeCarRepository();
        fakeCarRepository.save(new Car(1, "VIN113", "Toyota", "Yaris", CarStatus.AVAILABLE, BigDecimal.valueOf(500)));
        CarService carService = new CarService(fakeCarRepository);

        carService.markCarAsRented(1);

        assertEquals(CarStatus.UNAVAILABLE, fakeCarRepository.findById(1).getStatus());
    }

    @Test
    void shouldMarkCarAsAvailable() {
        FakeCarRepository fakeCarRepository = new FakeCarRepository();
        fakeCarRepository.save(new Car(1, "VIN123", "Toyota", "Yaris", CarStatus.UNAVAILABLE, BigDecimal.valueOf(500)));
        CarService carService = new CarService(fakeCarRepository);

        carService.markCarAsAvailable(1);

        assertEquals(CarStatus.AVAILABLE, fakeCarRepository.findById(1).getStatus());
    }

    @Test
    void shouldThrowExceptionWhenCarDoesNotExist() {
        FakeCarRepository fakeCarRepository = new FakeCarRepository();
        CarService carService = new CarService(fakeCarRepository);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> carService.markCarAsRented(99));

        assertEquals("Bil findes ikke", exception.getMessage());
    }

    @Test
    void shouldCountAvailableCars() {
        FakeCarRepository fakeCarRepository = new FakeCarRepository();
        fakeCarRepository.save(new Car(1, "VIN111", "Toyota", "Yaris", CarStatus.AVAILABLE, BigDecimal.valueOf(500)));
        fakeCarRepository.save(new Car(2, "VIN222", "Ford", "Fiesta", CarStatus.UNAVAILABLE, BigDecimal.valueOf(600)));
        fakeCarRepository.save(new Car(3, "VIN333", "VW", "Polo", CarStatus.AVAILABLE, BigDecimal.valueOf(700)));
        CarService carService = new CarService(fakeCarRepository);

        long availableCars = carService.countAvailableCars();

        assertEquals(2, availableCars);
    }

    private static class FakeCarRepository implements CarRepository {
        private final List<Car> cars = new ArrayList<>();

        @Override
        public List<Car> findAll() {
            return cars;
        }

        @Override
        public Car findById(int id) {
            return cars.stream()
                    .filter(car -> car.getId() == id)
                    .findFirst()
                    .orElse(null);
        }

        @Override
        public void save(Car car) {
            cars.add(car);
        }

        @Override
        public void updateStatus(int carId, CarStatus status) {
            Car car = findById(carId);
            if (car == null) {
                return;
            }

            switch (status) {
                case AVAILABLE -> car.markAsAvailable();
                case UNAVAILABLE -> car.markAsUnavailable();
                case RETURNED -> car.markAsReturned();
                case DAMAGED -> car.markAsDamaged();
                case UNDER_REPAIR -> car.markAsUnderRepair();
            }
        }
    }
}
