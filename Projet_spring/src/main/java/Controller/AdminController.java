package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import Entity.Admin;
import Service.AdminService;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/admin")
    public Admin getAdmin() {
        return adminService.getAdmin()
                .orElseThrow(() -> new RuntimeException("No admin found"));
    }
}
