package OnlineShopAppSpring.controllers;

import OnlineShopAppSpring.models.dtos.UserLoginBindingModel;
import OnlineShopAppSpring.models.dtos.UserRegisterBindingModel;
import OnlineShopAppSpring.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
@ModelAttribute
UserRegisterBindingModel userRegisterBindingModel(){
        return  new UserRegisterBindingModel();
}
    @ModelAttribute
    UserLoginBindingModel userLoginBindingModel(){
        return  new UserLoginBindingModel();
    }
    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }
    @PostMapping("/login")
    public ModelAndView loginPost(UserLoginBindingModel userLoginBindingModel){
      boolean isLogged =  userService.login(userLoginBindingModel);
String view = isLogged ? "redirect:/" : "login";
        return new ModelAndView(view);
    }

    @GetMapping("/register")
    public ModelAndView register(){
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView registerPost(@Valid UserRegisterBindingModel
                                                 userRegisterBindingModel,
                                     RedirectAttributes redirectAttributes,
                                     BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel",
                    userRegisterBindingModel);
redirectAttributes.addFlashAttribute("org.springframework" +
                ".validation.BindingResult" +
                ".userRegisterBindingModel"
        , bindingResult);

        }
        if (!userRegisterBindingModel.getPassword()
                .equals(userRegisterBindingModel.getConfirmPassword())) {

            redirectAttributes.addFlashAttribute("passwordDiff", true);

            redirectAttributes
                    .addFlashAttribute(
                            "userRegisterBindingModel", userRegisterBindingModel
                    );


        }
       boolean isRegistered = this.userService.register(userRegisterBindingModel);

     String view =  isRegistered ? "redirect:login" : "register";


        return new ModelAndView(view);

    }
    @GetMapping("/logout")
    public String logout(HttpSession httpSession){

        httpSession.invalidate();
        return "redirect:/";
    }

}
