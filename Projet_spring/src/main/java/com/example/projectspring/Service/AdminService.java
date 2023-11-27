package com.example.projectspring.Service;


import com.mysql.cj.protocol.a.CompressedInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.projectspring.Repository.AdminRepository;
import com.example.projectspring.Entity.Admin;



@Service

public class AdminService {


    /*
    private CustomerService cs;
    private CreditCardService ccs;
    private ProductService ps;
    private UserService us;
    */
    @Autowired
    private AdminRepository ar;




    /*


     Sets the dependencies for the service.*
     @param br  The BasketRepository to be injected.
     @param cs  The CustomerService to be injected.
     @param ccs The CreditCardService to be injected.
     @param ps  The ProductService to be injected.
     @param us  The UserService to be injected.
     */


    /*
    public void setDependencies(CompteRepository cr) {
        this.cr = cr;
    }*/


    /*
        this.cs = cs;
        this.ccs = ccs;
        this.ps = ps;
        this.us = us;*/


    public boolean emailExists(String email) {

        Admin admin = null;
        try {
            admin = ar.findByEmail(email);
            if (admin!= null) {
                return true;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getAdminEmail() {
        String adminEmail = null;
        try{
            Admin admin = ar.findAll().get(0);
            if (admin != null) {
                adminEmail = admin.getEmail();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminEmail;
    }










}