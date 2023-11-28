package com.example.projectspring.Controller;

import com.example.projectspring.Entity.Moderateur;
import com.example.projectspring.Service.ModeratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/edit-moderator-servlet") // Changer ceci avec le nom de votre servlet
public class EditModeratorController {

    @Autowired
    private ModeratorService moderateurService;

    @GetMapping
    public String showModerators(Model model) {
        List<Moderateur> listModerator = moderateurService.getListModerateur();
        model.addAttribute("moderators", listModerator);
        return "formEditModerateur";
    }

    @PostMapping
    public String updateModerator(
            @RequestParam("email") String email,
            @RequestParam("addP") byte addProd,
            @RequestParam("deleteP") byte deleteProd,
            @RequestParam("maxProd") int maxProd,
            Model model
    ) {
        try {
            Moderateur updateMod = new Moderateur();
            updateMod.setEmail(email);
            updateMod.setPeutAjouterProduit(addProd);
            updateMod.setPeutSupprimerProduit(deleteProd);
            updateMod.setMaxProduitsLigne(maxProd);

            moderateurService.updateModerator(email,updateMod);

            return "redirect:/moderator-servlet"; // Changer cela selon votre configuration
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/error";
        }
    }
}
