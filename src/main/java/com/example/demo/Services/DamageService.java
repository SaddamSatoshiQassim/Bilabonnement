package com.example.demo.Services;

import com.example.demo.Models.DamageLine;
import com.example.demo.Models.DamageReport;
import com.example.demo.Repositories.DamageLineRepository;
import com.example.demo.Repositories.DamageReportRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DamageService {

    private final DamageReportRepository repository;
    private final DamageLineRepository damageLineRepository;

    public DamageService(DamageReportRepository repository,
                         DamageLineRepository damageLineRepository) {
        this.repository = repository;
        this.damageLineRepository = damageLineRepository;
    }

    public BigDecimal calculateTotalDamagePrice(List<DamageLine> damageLines) {
        return damageLines.stream()
                .map(DamageLine::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<DamageReport> getAllDamageReports() {
        return repository.findAll();
    }

    public DamageReport getDamageReportById(int id) {
        return repository.findById(id);
    }

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
    }

    public void updateDamageReport(DamageReport damageReport) {
        repository.update(damageReport);
    }

    public void deleteDamageReportById(int id) {
        repository.deleteById(id);
    }
}