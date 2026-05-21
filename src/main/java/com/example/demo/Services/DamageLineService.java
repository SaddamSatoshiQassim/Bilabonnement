package com.example.demo.Services;

import com.example.demo.Models.DamageLine;
import com.example.demo.Repositories.DamageLineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DamageLineService {

    private final DamageLineRepository damageLineRepository;

    public DamageLineService(DamageLineRepository damageLineRepository) {
        this.damageLineRepository = damageLineRepository;
    }

    public List<DamageLine> getAllDamageLines() {
        return damageLineRepository.findAll();
    }

    public DamageLine getDamageLineById(int id) {
        return damageLineRepository.findById(id);
    }

    public List<DamageLine> getDamageLinesByReportId(int reportId) {
        return damageLineRepository.findByReportId(reportId);
    }

    public void createDamageLine(DamageLine damageLine) {
        damageLineRepository.save(damageLine);
    }

    public void updateDamageLine(DamageLine damageLine) {
        damageLineRepository.update(damageLine);
    }

    public void deleteDamageLineById(int id) {
        damageLineRepository.deleteById(id);
    }
}