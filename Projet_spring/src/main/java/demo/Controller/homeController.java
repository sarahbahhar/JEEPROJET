package demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;




@Controller
@RequestMapping("/homeController")
public class homeController {

    @GetMapping
    public String doGet(){
        return "redirect:/signUp";
    }
}
