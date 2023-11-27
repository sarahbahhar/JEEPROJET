package com.example.projectspring.Controller;


import com.example.projectspring.Entity.Compte;
import com.example.projectspring.Service.CompteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;


@Controller
@RequestMapping("/inscriptionController")
@SessionAttributes({"email", "nom", "prenom", "password"})


public class InscriptionController {/*
    @PostMapping
    public String doPost(@RequestParam("action") String action,
                         @RequestParam(value = "productId", required = false) Integer productId,
                         @RequestParam(value = "imgFile", required = false) MultipartFile[] imgFileArray,
                         @RequestParam(value = "img", required = false) String[] imgString,
                         @RequestParam(value = "name", required = false) String[] nameString,
                         @RequestParam(value = "price", required = false) String[] priceString,
                         @RequestParam(value = "stock", required = false) String[] stockString,
                         Model model) {
        @GetMapping
        public String doGet(Model model, SessionStatus sessionStatus) {
            // Get the session and remove the user attribute to log them out
            model.addAttribute("user", null);
            sessionStatus.setComplete();
            return "redirect:/Index";
        }*/

    @Autowired
    private CompteService cs;






    @GetMapping

    public String doGet(@RequestParam("email") String email,
                         @RequestParam("password") String password,
                         @RequestParam("nom") String nom,
                         @RequestParam("prenom") String prenom, Model model,HttpSession session){
        try{

            Compte compte = new Compte();
            compte.setEmail(email);
            compte.setMotDePasse(password);


            cs.addCompte(compte);

            session.setAttribute("email", email);
            session.setAttribute("nom", nom);
            session.setAttribute("prenom", prenom);
            session.setAttribute("password", password);

            return "infoCompte";// ?


        }catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";

    }
        /* http://localhost:8080/inscriptionController?email=test@gmail.com&password=test&nom=test&prenom=test
          */


    }


/*
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            //if(DAO.CompteDAO.isUniqueEmail(email)){





                //String htmlContent = getHtmlContentFromJsp("/mail/mailOfWelcome.jsp", request, response);

                //sendEmailWithHTML(email,"Bienvenue sur Divan",htmlContent);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/infoCompte.jsp");
                dispatcher.forward(request, response);
            }
            else{
                request.setAttribute("failEmailExist",true);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/signIn2.jsp");
                dispatcher.forward(request, response);

            }



        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/error");
            e.printStackTrace();
        }
    }
    */

/*
    void sendEmailWithHTML(String recipientEmail,String subject, String content) throws ServletException {
        final String username = "projetjee.cytech@gmail.com";
        final String myPassword = "idgz udpj wywr dfpn ";//code d'application

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Créer une session de messagerie avec les propriétés configurées
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, myPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);

            message.setContent(content, "text/html; charset=utf-8");

            Transport.send(message);
        } catch (MessagingException e) {
            throw new ServletException("Erreur lors de l'envoi de l'e-mail de confirmation", e);
        }
    }
    private String getHtmlContentFromJsp(String filePath, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringWriter stringWriter = new StringWriter();
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher(filePath);

        dispatcher.include(request, new HttpServletResponseWrapper(response) {
            public PrintWriter getWriter() {
                return new PrintWriter(stringWriter);
            }
        });

        return stringWriter.toString();
    }
    */


}

