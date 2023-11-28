package com.example.projectspring.Controller;

import com.example.projectspring.Service.ModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/bannir-moderator-servlet")
public class BannirModeratorController {

    @Autowired
    private ModeratorService moderatorService;

    @PostMapping
    public String bannirModerator(@RequestParam("email") String email,
                                  @RequestParam("motifCourt") String motifCourt,
                                  @RequestParam("motifLong") String motifLong,
                                  @RequestParam("dateFinBan") String dateFinBan) {
        try {
            //Date dateFinBan = new SimpleDateFormat("yyyy-MM-dd").parse(dateFinBanString);
            moderatorService.bannirByEmail(email, motifCourt, motifLong, dateFinBan);

            // Redirection ou gestion de la réponse après le bannissement
            return "redirect:/moderator-servlet";
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer l'erreur de conversion de date
            return "redirect:/error";
        }
    }
}
