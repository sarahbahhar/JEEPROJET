package com.example.projectspring.Repository;

import com.example.projectspring.Entity.Demandemoderateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeModerateurRepository extends JpaRepository<Demandemoderateur, String> {

    void deleteByEmail(String email);

    boolean existsByEmail(String email);
}
