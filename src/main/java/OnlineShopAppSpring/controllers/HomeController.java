package OnlineShopAppSpring.controllers;

import OnlineShopAppSpring.models.dtos.OfferHomeDto;
import OnlineShopAppSpring.services.LoggedUser;
import OnlineShopAppSpring.services.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
private final LoggedUser loggedUser;
private final OfferService offerService;
    public HomeController(LoggedUser loggedUser, OfferService offerService) {
        this.loggedUser = loggedUser;
        this.offerService = offerService;
    }

    @GetMapping("/")
    public ModelAndView index(){
        if (loggedUser.isLogged()){
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("index");
    }

    @GetMapping("/home")
    public String home(Model model){

        if (!loggedUser.isLogged()){
            return  "redirect:/login";
        }

        OfferHomeDto offersFotHomePage = offerService.getOffersFotHomePage();

        model.addAttribute("offerHomeDTO",offersFotHomePage);


        return "home";
    }




}
