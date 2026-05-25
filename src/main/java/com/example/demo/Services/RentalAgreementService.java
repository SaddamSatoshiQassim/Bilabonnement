package com.example.demo.Services;

import com.example.demo.Models.RentalAgreement;
import com.example.demo.Repositories.RentalAgreementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RentalAgreementService {

    private final RentalAgreementRepository repository;

    public RentalAgreementService(RentalAgreementRepository repository) {
        this.repository = repository;
    }

    public List<RentalAgreement> getAllAgreements() {
        return repository.findAll();
    }

    @Transactional
    public void addAgreement(RentalAgreement agreement) {

        if (agreement.getCustomerId() <= 0) {
            throw new IllegalArgumentException(
                    "Du skal vælge en kunde");
        }

        if (agreement.getCarId() <= 0) {
            throw new IllegalArgumentException(
                    "Du skal vælge en bil");
        }

        if (agreement.getStartDate() == null) {
            throw new IllegalArgumentException(
                    "Startdato skal udfyldes");
        }

        if (agreement.getEndDate() != null &&
                agreement.getEndDate().isBefore(
                        agreement.getStartDate())) {

            throw new IllegalArgumentException(
                    "Slutdato kan ikke være før startdato");
        }

        if (agreement.getRentalPrice() == null ||
                agreement.getRentalPrice().doubleValue() < 0) {

            throw new IllegalArgumentException(
                    "Prisen kan ikke være negativ");
        }

        repository.save(agreement);
    }
}