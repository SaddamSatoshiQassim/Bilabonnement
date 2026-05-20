package com.example.demo.Repositories;

import com.example.demo.Models.DamageLine;

import java.util.List;

public interface DamageLineRepository {

    List<DamageLine> findAll();

    DamageLine findById(int id);

    void save(DamageLine damageLine);

    void update(DamageLine damageLine);

    void deleteById(int id);
    List<DamageLine> findByReportId(int reportId);
}