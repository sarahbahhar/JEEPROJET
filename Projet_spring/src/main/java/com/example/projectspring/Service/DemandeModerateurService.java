package com.example.projectspring.Service;

import com.example.projectspring.Entity.Demandemoderateur;
import com.example.projectspring.Repository.DemandeModerateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DemandeModerateurService {

    @Autowired
    private DemandeModerateurRepository dmr;

    public void addModerator(Demandemoderateur demandeModerateur) {
        dmr.save(demandeModerateur);
    }

    public List<Demandemoderateur> getListModerateurWaiting() {
        return dmr.findAll();
    }

    @Modifying
    @Transactional
    public void removeDismissedModerator(String email) {
        dmr.deleteByEmail(email);
    }

    public boolean isEmailInModeratorRequests(String email) {
        return dmr.existsByEmail(email);
    }
}
