package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/sign-out-servlet")
public class SignOutController {

    @GetMapping
    public String signOut(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/"; // Redirige vers la page d'accueil ou vers une autre URL de votre choix
    }
}
