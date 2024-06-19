package OnlineShopAppSpring.controllers;

import OnlineShopAppSpring.models.dtos.OfferAddDto;
import OnlineShopAppSpring.models.service.OfferServiceModel;
import OnlineShopAppSpring.services.LoggedUser;
import OnlineShopAppSpring.services.OfferService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/offers")
public class OfferController {
private final OfferService offerService;
private final ModelMapper modelMapper;
private final LoggedUser loggedUser;
    public OfferController(OfferService offerService, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute
    OfferAddDto offerAddDto(){
        return  new OfferAddDto();
    }
    @GetMapping("/create")
    public String addOffer(Model model, HttpSession httpSession){
        if (httpSession.getAttribute("user") == null){
            return "redirect:/login";
        }
        if (!model.containsAttribute("offerAddDto"))
            model.addAttribute("offerAddDto",offerAddDto());
        return "offer-add";
    }

@PostMapping("/create")
    public String addConfirm(@Valid OfferAddDto offerAddDto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute(
                    "offerAddDto", offerAddDto);
            redirectAttributes.addFlashAttribute(
                    "org.springframework" +
                            ".validation.BindingResult" +
                            ".offerAddDto"
                    , bindingResult);

            return "redirect:create";
        }
        offerService.addOffer(modelMapper.map(offerAddDto, OfferServiceModel.class));
return "redirect:/home";
    }

//    @GetMapping("/remove/all")
//public String removeOffers(){
//        offerService.removeAllOffers();
//        return "redirect:/";
//    }

    @PostMapping("buy/{id}")
    public String buyOffer(@PathVariable("id")UUID id){
        if (!loggedUser.isLogged()){
            return "redirect:/login";
        }
        offerService.buy(id);
       // offerService.removeAllOffers();
        return "redirect:/home";
    }
}
