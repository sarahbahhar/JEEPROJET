package com.example.projectspring.Controller;

import com.example.projectspring.Service.ModeratorService;
import com.example.projectspring.Entity.Moderateur;
import com.example.projectspring.Service.ModeratorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/moderator-servlet")
public class ModeratorController {

    @Autowired
    private ModeratorService moderateurService;

    @GetMapping
    public String listModerators(Model model) {
        List<Moderateur> listModerator = moderateurService.getListModerateur();

        Map<String, BigDecimal> averageRatings = new HashMap<>();
        for (Moderateur moderator : listModerator) {
            BigDecimal averageRating = moderateurService.getAverageRatingByEmail(moderator.getEmail());
            averageRatings.put(moderator.getEmail(), averageRating);
        }

        model.addAttribute("moderators", listModerator);
        model.addAttribute("averageRatings", averageRatings);

        return "moderatorList";
    }
}
