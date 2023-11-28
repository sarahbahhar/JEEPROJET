package com.example.projectspring.Controller;

import com.example.projectspring.Service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import jakarta.servlet.ServletContext;

@Controller
@RequestMapping("/delete-product-servlet")
public class DeleteProductController {

    @Autowired
    private ProduitService produitService;

    @Autowired
    private ResourceLoader resourceLoader;

    @PostMapping
    public String deleteProduct(@RequestParam("email") String email, @RequestParam("id") String idStr, Model model) {
        Integer id=Integer.parseInt(idStr);
        try {
            if (id != null) {
                String realPath =resourceLoader.getResource("classpath:/").getFile().getAbsolutePath();
                produitService.removeProductById(realPath, produitService.getProduitById(id).getNomImage(),produitService.getProduitById(id).getNomImage2(),id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }
        return "redirect:/my-product-list-servlet?email=" + email;
    }
}
