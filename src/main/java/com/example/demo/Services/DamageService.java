package com.example.demo.Services;

import com.example.demo.Models.DamageLine;
import com.example.demo.Models.DamageReport;
import com.example.demo.Models.RentalAgreement;
import com.example.demo.Repositories.DamageLineRepository;
import com.example.demo.Repositories.DamageReportRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DamageService {

    private final DamageReportRepository repository;
    private final DamageLineRepository damageLineRepository;
    private final RentalAgreementService rentalAgreementService;
    private final CarService carService;

    public DamageService(DamageReportRepository repository,
                         DamageLineRepository damageLineRepository,
                         RentalAgreementService rentalAgreementService,
                         CarService carService) {
        this.repository = repository;
        this.damageLineRepository = damageLineRepository;
        this.rentalAgreementService = rentalAgreementService;
        this.carService = carService;
    }

    public BigDecimal calculateTotalDamagePrice(List<DamageLine> damageLines) {
        return damageLines.stream()
                .map(DamageLine::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<DamageReport> getAllDamageReports() {

        List<DamageReport> damageReports =
                repository.findAll();

        for (DamageReport report : damageReports) {

            List<DamageLine> damageLines =
                    damageLineRepository.findByReportId(
                            report.getId()
                    );

            report.setDamageLines(
                    damageLines
            );

            report.setTotalPrice(
                    calculateTotalDamagePrice(
                            damageLines
                    )
            );
        }

        return damageReports;
    }

    public DamageReport getDamageReportById(int id) {
        return repository.findById(id);
    }

    @Transactional
    public void addDamageReport(DamageReport damageReport, List<String> damages) {

        int reportId = repository.saveAndReturnId(damageReport);

        if (damages != null) {
            for (String damage : damages) {
                String[] parts = damage.split(":");

                String description = parts[0];
                BigDecimal price = new BigDecimal(parts[1]);

                DamageLine line = new DamageLine(0, reportId, description, price);
                damageLineRepository.save(line);
            }
        }

        RentalAgreement rentalAgreement =
                rentalAgreementService.getAgreementById(damageReport.getRentalId());

        carService.markCarAsDamaged(rentalAgreement.getCarId());
    }

    @Transactional
    public void updateDamageReport(DamageReport damageReport) {
        repository.update(damageReport);
    }

    @Transactional
    public void deleteDamageReportById(int id) {
        repository.deleteById(id);
    }

}