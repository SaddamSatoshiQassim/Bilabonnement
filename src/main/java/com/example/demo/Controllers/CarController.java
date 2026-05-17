package com.example.demo.Controllers;

import com.example.demo.Services.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarController {

    private final CarService service;

    public CarController(CarService service) {
        this.service = service;
    }

    @GetMapping("/biler")
    public String showCars(Model model) {

  //      model.addAttribute("cars", service.getAll());
        return "biler";
    }

    @GetMapping("/biler/udlejede")
    public String showRentedCars() {
        return "udlejede-biler";
    }

    @GetMapping("/biler/ledige")
    public String showAvailableCars() {
        return "ledige-biler";
    }

    @GetMapping("/biler/returnerede")
    public String showReturnedCars() {
        return "returnerede-biler";
    }
}
