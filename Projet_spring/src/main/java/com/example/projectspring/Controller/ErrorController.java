package com.example.projectspring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {

    @GetMapping("/error")
    public String handleError() {
        // Affichez la page d'erreur avec un message spécifique
        return "error";
    }
}
