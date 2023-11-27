package com.example.projectspring.Repository;

import com.example.projectspring.Entity.Panier;
import com.example.projectspring.Entity.Produitpanier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PanierRepository extends JpaRepository<Panier, String> {

    Optional<Panier> findByEmail(String email);
}

