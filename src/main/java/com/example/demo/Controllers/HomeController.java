
package com.example.demo.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.Services.CarService;
import org.springframework.ui.Model;

@Controller
public class HomeController {

    private final CarService carService;
    public HomeController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/")
    public String index(HttpSession session,  Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }

        model.addAttribute("availableCount", carService.countAvailableCars());
        model.addAttribute("rentedCount", carService.countRentedCars());
        model.addAttribute("damagedCount", carService.countDamagedCars());
        model.addAttribute("cars", carService.getAll());


        return "index";
    }
}
