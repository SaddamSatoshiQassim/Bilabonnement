package com.example.demo.Services;

import com.example.demo.Models.DamageLine;
import com.example.demo.Models.DamageReport;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DamageService {

    private final List<DamageReport> damageReports = new ArrayList<>();

    public double calculateTotalDamagePrice(List<DamageLine> damageLines) {
        return damageLines.stream()
                .mapToDouble(DamageLine::getPrice)
                .sum();
    }

    public List<DamageReport> getAllDamageReports() {
        return new ArrayList<>(damageReports);
    }

    public DamageReport getDamageReportById(int id) {
        return damageReports.stream()
                .filter(report -> report.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addDamageReport(DamageReport damageReport) {
        damageReports.removeIf(report -> report.getId() == damageReport.getId());
        damageReports.add(damageReport);
    }

    public void updateDamageReport(DamageReport damageReport) {
        addDamageReport(damageReport);
    }

    public void deleteDamageReportById(int id) {
        damageReports.removeIf(report -> report.getId() == id);
    }
}
