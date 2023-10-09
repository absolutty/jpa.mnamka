package sk.uniza.fri.mnamka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import sk.uniza.fri.mnamka.model.UserModel;
import sk.uniza.fri.mnamka.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String showUserPage(Model model) {
        model.addAttribute("registerRequest", new UserModel());


        return NavigationController.getPageWithPath("login");
    }


    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerRequest", new UserModel());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new UserModel());
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserModel userModel) {
        System.out.println("register request: ");
        UserModel registeredUser = userService.registerUser(userModel.getName(), userModel.getLastName(),  userModel.getEmail(), userModel.getPassword());

        return registeredUser == null ? "error_page" : "login_page";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserModel userModel, Model model) {
        System.out.println("login request: ");
        UserModel authenticated = userService.authenticate(userModel.getEmail(), userModel.getPassword());

        if (authenticated != null) {
            model.addAttribute("email", authenticated.getEmail());
            return "personal_page";
        } else {
            return "error_page";
        }
    }
}
