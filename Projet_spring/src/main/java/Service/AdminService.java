package Service;

import java.util.Optional;

import Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Entity.Admin;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Optional<Admin> getAdmin() {
        return adminRepository.findAll().stream().findFirst();
    }
}
