package Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Repository.CompteRepository;
import Entity.Compte;
@Service

public class CompteService {


    /*
    private CustomerService cs;
    private CreditCardService ccs;
    private ProductService ps;
    private UserService us;
    */
    @Autowired
    private CompteRepository cr;


    /*


     Sets the dependencies for the service.*
     @param br  The BasketRepository to be injected.
     @param cs  The CustomerService to be injected.
     @param ccs The CreditCardService to be injected.
     @param ps  The ProductService to be injected.
     @param us  The UserService to be injected.
     */

    public void setDependencies(CompteRepository cr/* CustomerService cs, CreditCardService ccs, ProductService ps, UserService us*/) {
        this.cr = cr;
    }

    /*
        this.cs = cs;
        this.ccs = ccs;
        this.ps = ps;
        this.us = us;*/



    public void addCompte(Compte compte) {
        cr.save(compte);
    }



}




