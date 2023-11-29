package com.example.projectspring.Controller;

import com.example.projectspring.Service.CompteService;
import com.example.projectspring.Service.TokenService;
import com.example.projectspring.Util.EmailSender;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/PasswordForgetServlet")
public class PasswordForgetController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private CompteService compteService;
    @Autowired
    private ResourceLoader resourceLoader;


    @GetMapping
    public ModelAndView showResetPasswordForm(@RequestParam String resetToken, @RequestParam String email) {
        System.out.println("Voici le resetToken : " + resetToken +"et Voic l'email : " + email);
        ModelAndView modelAndView = new ModelAndView();
        if (tokenService.isValidToken(email, resetToken)) {
            modelAndView.addObject("email", email);
            modelAndView.setViewName("resetPassword");
        } else {
            modelAndView.setViewName("lienExpire");
        }
        return modelAndView;
    }

    @PostMapping
    public ModelAndView handlePasswordResetRequest(@RequestParam String email) throws IOException, MessagingException {
        ModelAndView modelAndView = new ModelAndView();
        if (compteService.isUniqueEmail(email)) {
            String resetToken = TokenService.generateResetToken();
            tokenService.changeTokenByEmail(email,resetToken);
            String resetLink = "http://localhost:8080/PasswordForgetServlet?resetToken=" + resetToken + "&email=" + email;


            modelAndView.addObject("resetLink", resetLink);
            System.out.println((resetLink));
            String realPath =resourceLoader.getResource("classpath:/").getFile().getAbsolutePath();
            EmailSender.sendPasswordResetEmail(realPath,email, resetLink);
            modelAndView.setViewName("mailSend");
        } else {
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }

    // Other methods...
}
