package com.example.demo.Controllers;

import com.example.demo.Models.DamageReport;
import com.example.demo.Services.DamageService;
import com.example.demo.Services.RentalAgreementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DamageReportController {

    private final DamageService damageService;
    private final RentalAgreementService rentalAgreementService;

    public DamageReportController(
            DamageService damageService,
            RentalAgreementService rentalAgreementService) {

        this.damageService = damageService;
        this.rentalAgreementService = rentalAgreementService;
    }

    @GetMapping("/skader")
    public String getAllDamageReports(Model model) {

        model.addAttribute(
                "damageReports",
                damageService.getAllDamageReports());

        model.addAttribute(
                "rentalAgreements",
                rentalAgreementService.getAllAgreements());

        return "skader";
    }

    @GetMapping("/skader/opret")
    public String createForm(Model model) {

        model.addAttribute(
                "damageReport",
                new DamageReport(0,0,null,null));

        model.addAttribute(
                "rentalAgreements",
                rentalAgreementService.getAllAgreements());

        return "opret-skade";
    }

    @PostMapping("/skader/gem")
    public String saveDamageReport(
            @ModelAttribute DamageReport damageReport,
            @RequestParam(required = false)
            List<String> damage) {

        damageService.addDamageReport(
                damageReport,
                damage);

        return "redirect:/skader";
    }
}