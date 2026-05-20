package com.example.demo.Services;

import com.example.demo.Models.DamageLine;
import com.example.demo.Models.DamageReport;
import com.example.demo.Repositories.DamageReportRepository;
import com.example.demo.Repositories.JDBCDamageReportRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DamageService {

    private final DamageReportRepository repository;


    public DamageService(DamageReportRepository repository) {
        this.repository = repository;
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

    public void addDamageReport(DamageReport damageReport) {
        repository.save(damageReport);
    }

    public void updateDamageReport(DamageReport damageReport) {
        repository.update(damageReport);
    }

    public void deleteDamageReportById(int id) {
        repository.deleteById(id);
    }
}
