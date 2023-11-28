package com.example.projectspring.Controller;

import com.example.projectspring.Service.DemandeModerateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/delete-dismissed-mod-servlet")
public class DeleteDismissedWModController {

    @Autowired
    private DemandeModerateurService demandeModerateurService;

    @PostMapping
    public String deleteDismissedWMod(@RequestParam("email") String email) {
        try {
            if (email != null && !email.isEmpty()) {
                demandeModerateurService.removeDismissedModerator(email);
                return "redirect:/in-waiting-mod-servlet";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/error";
    }
}
