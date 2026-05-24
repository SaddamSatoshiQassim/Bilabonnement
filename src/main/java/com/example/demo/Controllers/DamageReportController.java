package com.example.demo.Controllers;

import com.example.demo.Models.DamageReport;
import com.example.demo.Services.CarService;
import com.example.demo.Services.CustomerService;
import com.example.demo.Services.DamageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DamageReportController {

    private final DamageService service;
    private final CarService carService;


    public DamageReportController(DamageService service, CarService carService) {
        this.carService = carService;
        this.service = service;
    }

    @GetMapping("/skader")
    public String getAllDamageReports(Model model) {
        model.addAttribute("damageReports", service.getAllDamageReports());
        return "skader";
    }

    @GetMapping("/skader/opret")
    public String createForm(Model model) {

        model.addAttribute("damageReport",
                new DamageReport(0, 0, null, null));

        model.addAttribute("cars", carService.getAll());

        return "opret-skade";
    }
    @PostMapping("/skader/gem")
    public String saveDamageReport(@ModelAttribute DamageReport damageReport,
                                   @RequestParam(required = false) List<String> damage) {

        service.addDamageReport(damageReport, damage);

        return "redirect:/skader";
    }
}