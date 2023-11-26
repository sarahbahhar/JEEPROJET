package Repository;

import Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.transaction.Transactional;



@Transactional
public interface AdminRepository extends JpaRepository<Admin, Long> {
    // Ajoutez des méthodes personnalisées si nécessaire, par exemple :
    boolean existsByEmail(String email);
    String findTopByEmail(); // Cette méthode retourne le premier email trouvé, vous pouvez ajuster selon vos besoins.
}