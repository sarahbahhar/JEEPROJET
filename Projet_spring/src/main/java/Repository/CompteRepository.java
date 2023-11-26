package Repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import Entity.Compte;
@Repository

public interface CompteRepository extends JpaRepository<Compte, String> {

/*
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Compte (email, mot_de_passe) VALUES (:email, :motDePasse)", nativeQuery = true)

    void addCompte(@Param("email") String email, @Param("motDePasse") String motDePasse);

*/
}