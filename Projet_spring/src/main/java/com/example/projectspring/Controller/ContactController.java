package com.example.projectspring.Controller;

import com.example.projectspring.Service.AdminService;
import com.example.projectspring.Util.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/contact-servlet")
public class ContactController {

    @Autowired
    private AdminService as;
    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping
    public String showContactForm() {
        return "contactForm"; // name of the JSP file
    }

    @PostMapping
    public ModelAndView sendEmail(@RequestParam("emailCompte") String emailCompte,
                                  @RequestParam("message") String message) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            String emailAdmin = as.getAdminEmail();
            String realPath =resourceLoader.getResource("classpath:/").getFile().getAbsolutePath();
            EmailSender.sendContact(realPath,emailAdmin,emailCompte, message);
            modelAndView.setViewName("mailSendContact"); // The JSP page to display
        } catch (Exception e) {
            modelAndView.setViewName("error"); // Error page
        }
        return modelAndView;
    }

}
