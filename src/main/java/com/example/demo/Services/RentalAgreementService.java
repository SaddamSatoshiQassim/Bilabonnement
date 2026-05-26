package com.example.demo.Services;

import com.example.demo.Models.Car;
import com.example.demo.Models.CarStatus;
import com.example.demo.Models.RentalAgreement;
import com.example.demo.Repositories.RentalAgreementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RentalAgreementService {

    private final RentalAgreementRepository repository;
    private final CarService carService;

    public RentalAgreementService(RentalAgreementRepository repository, CarService carService) {
        this.repository = repository;
        this.carService = carService;
    }

    public List<RentalAgreement> getAllAgreements() {
        return repository.findAll();
    }

    public RentalAgreement getAgreementById(int id) {
        return repository.findById(id);
    }

    @Transactional
    public void saveOrUpdateAgreement(RentalAgreement agreement) {

        if (agreement.getCustomerId() <= 0) {
            throw new IllegalArgumentException("Du skal vælge en kunde");
        }

        if (agreement.getCarId() <= 0) {
            throw new IllegalArgumentException("Du skal vælge en bil");
        }

        if (agreement.getStartDate() == null) {
            throw new IllegalArgumentException("Startdato skal udfyldes");
        }

        if (agreement.getEndDate() == null) {
            throw new IllegalArgumentException("Slutdato skal udfyldes");
        }

        if (agreement.getStartDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Startdato skal være fra dagsdato eller frem");
        }

        if (agreement.getEndDate().isBefore(agreement.getStartDate())) {
            throw new IllegalArgumentException("Slutdato kan ikke være før startdato");
        }

        Car car = carService.findById(agreement.getCarId());

        if (car == null) {
            throw new IllegalArgumentException("Den valgte bil findes ikke");
        }

        if (car == null) {
            throw new IllegalArgumentException("Bilen findes ikke");
        }

        if (car.getStatus() != CarStatus.AVAILABLE) {
            throw new IllegalArgumentException("Bilen er allerede udlejet eller ikke ledig");
        }

        long days = ChronoUnit.DAYS.between(
                agreement.getStartDate(),
                agreement.getEndDate()
        );

        if (days <= 0) {
            days = 1;
        }

        BigDecimal totalPrice = car.getBasePrice()
                .multiply(BigDecimal.valueOf(days));

        agreement.setRentalPrice(totalPrice);

        if (agreement.getId() == 0) {
            repository.save(agreement);
            carService.markCarAsRented(agreement.getCarId());
        } else {
            repository.update(agreement);
        }

        carService.markCarAsRented(agreement.getCarId());
    }

    public void addAgreement(RentalAgreement agreement) {
        saveOrUpdateAgreement(agreement);
    }
}
