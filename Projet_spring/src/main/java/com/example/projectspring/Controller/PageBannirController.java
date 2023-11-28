package com.example.projectspring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageBannirController {

    @GetMapping("/page-bannir-servlet")
    public String showBannirPage() {
        return "bannir";
    }

    @PostMapping("/page-bannir-servlet")
    public String pageBannir(@RequestParam("email") String email, Model model) {
        model.addAttribute("emailModerateur", email);
        return "bannir";
    }
}
