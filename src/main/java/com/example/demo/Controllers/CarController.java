package com.example.demo.Controllers;

import com.example.demo.Services.CarService;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/biler")
    public String showCars(Model model, HttpSession session) {

        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }

        model.addAttribute("cars", carService.getAll());

        return "biler";
    }

    @GetMapping("/biler/udlejede")
    public String showRentedCars(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }

        return "udlejede-biler";
    }

    @GetMapping("/biler/ledige")
    public String showAvailableCars(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }

        return "ledige-biler";
    }

    @GetMapping("/biler/returnerede")
    public String showReturnedCars(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }

        return "returnerede-biler";
    }
    @PostMapping("/biler/ledig")
    public String makeCarAvailable(@RequestParam int carId) {
        carService.markCarAsAvailable(carId);
        return "redirect:/biler";
    }
}
