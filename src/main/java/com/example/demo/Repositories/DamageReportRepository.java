package com.example.demo.Repositories;

import com.example.demo.Models.DamageReport;

import java.util.List;

public interface DamageReportRepository {

    public List<DamageReport> findAll();

    public DamageReport findById(int id);

    public void save(DamageReport damageReport);

    public void update(DamageReport damageReport);

    public void deleteById(int id);
}

