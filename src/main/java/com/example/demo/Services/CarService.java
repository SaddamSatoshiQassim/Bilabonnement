package com.example.demo.Services;

import com.example.demo.Models.Car;
import com.example.demo.Models.CarStatus;
import com.example.demo.Repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class

CarService {

    private final CarRepository carRepository;
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public List<Car> getAll() {
        return this.carRepository.findAll();
    }

    public Car getById(int id) {
        return this.carRepository.findById(id);
    }

    public void save(Car car) {
        if(car == null){
            throw new NullPointerException("Bil må ikke være null");
        }
        carRepository.save(car);
    }

    //update status
    public void markCarAsAvailable(int carId){
        Car car = carRepository.findById(carId);

        if(car == null){
            throw new RuntimeException("Bil findes ikke");
        }

        car.markAsAvailable();

        carRepository.updateStatus(
                carId,
                CarStatus.AVAILABLE
        );
    }

    public void markCarAsRented(int carId){

        Car car = carRepository.findById(carId);

        if(car == null){
            throw new RuntimeException("Bil findes ikke");
        }

        car.markAsUnavailable();

        carRepository.updateStatus(
                carId,
                CarStatus.UNAVAILABLE
        );
    }

    public void markCarAsReturned(int carId){

        Car car = carRepository.findById(carId);

        if(car == null){
            throw new RuntimeException("Bil findes ikke");
        }

        car.markAsReturned();

        carRepository.updateStatus(
                carId,
                CarStatus.RETURNED
        );
    }

    public void markCarAsDamaged(int carId){

        Car car = carRepository.findById(carId);

        if(car == null){
            throw new RuntimeException("Bil findes ikke");
        }

        car.markAsDamaged();

        carRepository.updateStatus(
                carId,
                CarStatus.DAMAGED
        );
    }

    public void markCarAsUnderRepair(int carId){

        Car car = carRepository.findById(carId);

        if(car == null){
            throw new RuntimeException("Bil findes ikke");
        }

        car.markAsUnderRepair();

        carRepository.updateStatus(
                carId,
                CarStatus.UNDER_REPAIR
        );
    }

    //Viser status af biler på index
    public long countAvailableCars(){

        return carRepository.findAll()
                .stream()
                .filter(car -> car.getStatus() == CarStatus.AVAILABLE)
                .count();
    }

    public long countRentedCars(){

        return carRepository.findAll()
                .stream()
                .filter(car -> car.getStatus() == CarStatus.UNAVAILABLE)
                .count();
    }

    public long countReturnedCars(){

        return carRepository.findAll()
                .stream()
                .filter(car -> car.getStatus() == CarStatus.RETURNED)
                .count();
    }

    public long countDamagedCars(){

        return carRepository.findAll()
                .stream()
                .filter(car -> car.getStatus() == CarStatus.DAMAGED)
                .count();
    }

    //Filter listerne
    public List<Car> getAvailableCars(){

        return carRepository.findAll()
                .stream()
                .filter(car -> car.getStatus() == CarStatus.AVAILABLE)
                .toList();
    }

    public List<Car> getRentedCars(){

        return carRepository.findAll()
                .stream()
                .filter(car -> car.getStatus() == CarStatus.UNAVAILABLE)
                .toList();
    }

    public List<Car> getReturnedCars(){

        return carRepository.findAll()
                .stream()
                .filter(car -> car.getStatus() == CarStatus.RETURNED)
                .toList();
    }

    public List<Car> getDamagedCars(){

        return carRepository.findAll()
                .stream()
                .filter(car -> car.getStatus() == CarStatus.DAMAGED)
                .toList();
    }

//Til søgning
    public List<Car> searchCars(String keyword){

        return carRepository.findAll()
                .stream()
                .filter(car -> car.getBrand().toLowerCase().contains(keyword.toLowerCase())
                        ||
                        car.getModel().toLowerCase().contains(keyword.toLowerCase())

                        ||
                        car.getVin().toLowerCase().contains(keyword.toLowerCase())
                ).toList();
    }
}
