import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/loading.jsp")
    public String loading() {
        return "loading"; // Notez que le suffixe ".jsp" est géré par la configuration Spring MVC.
    }

    @GetMapping("header.jsp")
    public String header(){
        return "header";
    }
    @GetMapping("/home.jsp")
    public String home() {
        return "home"; // Notez que le suffixe ".jsp" est géré par la configuration Spring MVC.
    }



}