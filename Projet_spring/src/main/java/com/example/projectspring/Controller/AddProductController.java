package com.example.projectspring.Controller;

import com.example.projectspring.Entity.Produit;
import com.example.projectspring.Service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

@Controller
@RequestMapping("/add-product-servlet")
public class AddProductController {

    @Autowired
    private ProduitService produitService;
    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping
    public String showAddProductForm(Model model) {
        return "addProduct"; // Assurez-vous que "addProduct" est le nom de votre vue (à créer dans /src/main/resources/templates)
    }

    @PostMapping
    public String addProduct(@RequestParam("titre") String titre,
                             @RequestParam("miniDescription") String miniDescription,
                             @RequestParam("categorie") String categorie,
                             @RequestParam("price") String price,
                             @RequestParam("description") String description,
                             @RequestParam("stock") int stock,
                             @RequestParam("email") String email,
                             @RequestParam("image") MultipartFile image,
                             @RequestParam("image2") MultipartFile image2,
                             Model model) {

        try {
            Produit p = new Produit();
            p.setTitre(titre);
            p.setMiniDescription(miniDescription);
            p.setCategorie(categorie);

            BigDecimal prix = new BigDecimal(price);
            p.setPrix(prix);

            p.setDescription(description);
            p.setStock(stock);
            p.setEmail(email);
            String realPath = resourceLoader.getResource("classpath:/").getFile().getAbsolutePath();


            if (!image.isEmpty() && !image2.isEmpty()) {

                produitService.addFullProduct(realPath,p, image,image2);

            }




            produitService.addProduct(p);






        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de l'ajout du produit");
            return "error"; // Assurez-vous que "error" est le nom de votre vue d'erreur (à créer dans /src/main/resources/templates)
        }

        return "redirect:/my-product-list-servlet?email=" + email;
    }


}
