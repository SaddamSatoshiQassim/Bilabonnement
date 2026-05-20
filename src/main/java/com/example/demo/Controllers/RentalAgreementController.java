package com.example.demo.Controllers;

import com.example.demo.Models.RentalAgreement;
import com.example.demo.Services.CarService;
import com.example.demo.Services.CustomerService;
import com.example.demo.Services.RentalAgreementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;

@Controller
public class RentalAgreementController {

    private final CarService carService;
    private final CustomerService customerService;
    private final RentalAgreementService service;

    public RentalAgreementController(RentalAgreementService service, CarService carService, CustomerService customerService) {
        this.service = service;
        this.carService = carService;
        this.customerService = customerService;
    }

    @GetMapping("/aftaler")
    public String getAllAgreements(Model model) {
        model.addAttribute("agreements", service.getAllAgreements());
        return "aftaler";
    }

    @GetMapping("/aftaler/opret")
    public String createAgreement(Model model) {

        model.addAttribute("cars", carService.getAll());
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("agreement", new RentalAgreement());

        return "opret-aftale";
    }

    @PostMapping("/aftaler/gem")
    public String saveAgreement(@ModelAttribute RentalAgreement agreement) {
        service.addAgreement(agreement);
        return "redirect:/aftaler";
    }
}