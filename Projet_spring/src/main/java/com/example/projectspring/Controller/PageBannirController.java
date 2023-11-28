package com.example.projectspring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/page-bannir-servlet")
public class PageBannirController {

    @GetMapping
    public String showBannirPage() {
        return "bannir";
    }

    @PostMapping
    public String pageBannir(@RequestParam("email") String email, Model model) {
        model.addAttribute("emailModerateur", email);
        return "bannir";
    }
}
