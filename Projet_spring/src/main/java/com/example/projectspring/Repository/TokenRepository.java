package com.example.projectspring.Repository;

import com.example.projectspring.Entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository

public interface TokenRepository extends JpaRepository<Token, String> {
    Optional<Token> findByEmail(String email);

}
