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
        repository.save(agreement);
    }
}