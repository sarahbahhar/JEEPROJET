
package com.example.projectspring.Repository;

import com.example.projectspring.Entity.Moderateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository

public interface ModeratorRepository extends JpaRepository<Moderateur, String> {
    @Query(value="SELECT * FROM Admin WHERE email= :email", nativeQuery=true)
    Moderateur findByEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query("UPDATE Produitcommande SET emailVendeur = null WHERE emailVendeur = :email")
    void updateVendeur(@Param("email") String emailVendeur);

    @Transactional
    @Modifying
    @Query(value="DELETE FROM Client WHERE email= :email", nativeQuery=true)
    void deleteByEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query(value="UPDATE Client SET points_fidelite = :points_fidelite WHERE email= :email", nativeQuery=true)
    void addPoint(@Param("points_fidelite") int pointFidelite, @Param("email") String email);

    @Query(value="SELECT AVG(c.note) FROM Commentaires c WHERE c.emailVendeur = :email", nativeQuery=true)
    Double getAverageRatingByEmail(@Param("email") String email);

}
