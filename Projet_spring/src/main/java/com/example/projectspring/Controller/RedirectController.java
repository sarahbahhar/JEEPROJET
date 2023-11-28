package com.example.projectspring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/redirect-servlet")
public class RedirectController {

    @GetMapping
    public String doGet(@RequestParam("path") String path) {
        return path;
    }

    @PostMapping
    public String doPost(@RequestParam("path") String path) {

        if (path != null && path.endsWith(".jsp")) {
            path = path.substring(0, path.length() - 4);
        }
        return path;
    }
}
