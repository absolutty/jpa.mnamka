package sk.uniza.fri.mnamka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import sk.uniza.fri.mnamka.model.UserModel;
import sk.uniza.fri.mnamka.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public RedirectView getUserPage() {
        boolean isLogged = false;
        if (isLogged) {  //TODO: if user is logged, go to user_page, else go to login page
            return null;
        } else {
            return new RedirectView("/user/login");
        }
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new UserModel());
        return NavigationController.getPageWithPath("user/login");
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserModel userModel, Model model) {
        System.out.println("login request: ");
        UserModel authenticated = userService.authenticate(userModel.getEmail(), userModel.getPassword());

        if (authenticated != null) {
            model.addAttribute("email", authenticated.getEmail());
            return "personal_page";
        } else {
            return "error";
        }
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerRequest", new UserModel());
        return NavigationController.getPageWithPath("user/register");
    }

    @PostMapping("/register")
    public RedirectView register(@ModelAttribute UserModel userModel) {
        UserModel registerUser = userService.registerUser(userModel.getName(), userModel.getLastName(),  userModel.getEmail(), userModel.getPassword());
        if (registerUser == null) {
            return new RedirectView("/error");
        } else {
            return new RedirectView("/user/login");
        }

    }
}
