package Servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;


@WebServlet(name = "ErrorServlet", value = "/error")
public class ErrorServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Page d'erreur avec un message spécifique
        out.println("<html><head><title>Erreur</title></head><body>");
        out.println("<h1>Une erreur s'est produite.</h1>");
        out.println("<p>Veuillez patienter, vous allez être redirigé vers la page d'accueil dans quelques instants.</p>");
        out.println("</body></html>");

        // Redirection vers la page d'accueil après 5 secondes
        response.setHeader("Refresh", "5; URL=/PROJETFINAL_war_exploded/product-servlet");
    }
}

//dans les catchs ou on veut renvoyer vers la servlet error utiliser : response.sendRedirect("/error")
