package sk.uniza.fri.mnamka.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;
import sk.uniza.fri.mnamka.controller.PageController;
import sk.uniza.fri.mnamka.helper.PathFormatter;
import sk.uniza.fri.mnamka.model.UserModel;
import sk.uniza.fri.mnamka.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController extends PageController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public PathFormatter getPathFormatter() {
        return new PathFormatter("pages/user/%s");
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
        return getPathFormatter().getPageNameWithPath("login");
    }

    @PostMapping("/login")
    public RedirectView login(@ModelAttribute UserModel userModel, Model model) {
        UserModel authenticated = userService.authenticate(userModel.getEmail(), userModel.getPassword());

        if (authenticated != null) {
            model.addAttribute("email", authenticated.getEmail());
            return new RedirectView("/user/personal_page");
        } else {
            return new RedirectView("/error");
        }
    }

    @GetMapping("/personal_page")
    public String personalPage() {
        return getPathFormatter().getPageNameWithPath("personal_page");
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerRequest", new UserModel());
        return getPathFormatter().getPageNameWithPath("register");
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
