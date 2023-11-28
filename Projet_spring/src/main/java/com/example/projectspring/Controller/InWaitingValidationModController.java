package com.example.projectspring.Controller;

import com.example.projectspring.Service.DemandeModerateurService;
import com.example.projectspring.Entity.Demandemoderateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/in-waiting-mod-servlet")
public class InWaitingValidationModController {

    @Autowired
    private DemandeModerateurService demandeModerateurService;

    @GetMapping
    public String showInWaitingModPage(Model model) {
        List<Demandemoderateur> listModerator = demandeModerateurService.getListModerateurWaiting();
        model.addAttribute("moderators", listModerator);
        return "moderatorWaitingList"; // Nom de la vue moderatorWaitingList.jsp
    }
}
