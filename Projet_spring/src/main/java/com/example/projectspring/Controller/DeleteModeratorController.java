package com.example.projectspring.Controller;

import com.example.projectspring.Service.ModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/delete-moderator-servlet")
public class DeleteModeratorController {

    @Autowired
    private ModeratorService moderatorService;

    @PostMapping
    public String deleteModerator(@RequestParam("email") String email) {
        try {
            if (email != null) {
                moderatorService.removeModerator(getServletContext().getRealPath("/"), email);
                return "redirect:/moderator-servlet";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/error";
    }
}
