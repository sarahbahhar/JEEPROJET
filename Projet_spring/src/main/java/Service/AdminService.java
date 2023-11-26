package Service;

import Repository.AdminRepository;
import jakarta.inject.Inject;

// Service ou Contrôleur Jakarta EE
public class AdminService {
    @Inject
    private AdminRepository adminRepository;

    public boolean emailExists(String email) {
        return adminRepository.existsByEmail(email);
    }

    public String getAdminEmail() {
        return adminRepository.findTopByEmail();
    }

    // Autres méthodes spécifiques si nécessaire
}
