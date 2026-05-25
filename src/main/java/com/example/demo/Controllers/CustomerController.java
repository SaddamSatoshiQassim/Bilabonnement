package com.example.demo.Controllers;

import com.example.demo.Models.Customer;
import com.example.demo.Services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/kunder")
    public String getAllCustomers(Model model) {
        model.addAttribute("customers", service.getAllCustomers());
        return "kunder";
    }

    @GetMapping("/kunder/opret")
    public String createForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "opret-kunde";
    }

    @PostMapping("/kunder/gem")
    public String saveCustomer(@ModelAttribute Customer customer) {
        service.addCustomer(customer);
        return "redirect:/kunder";
    }
}