package com.example.demo.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarController {

    @GetMapping("/biler")
    public String showCars(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }

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
}
