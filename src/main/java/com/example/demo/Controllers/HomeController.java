package com.example.demo.Controllers;

import com.example.demo.Services.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CarService carService;

    public HomeController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("availableCount", carService.countAvailableCars());
        model.addAttribute("rentedCount", carService.countRentedCars());
        model.addAttribute("damagedCount", carService.countDamagedCars());

        model.addAttribute("cars", carService.getAll());
        return "index";
    }
}
