package sk.uniza.fri.mnamka.controller.pages;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String getUserPage(HttpSession session) {
        UserModel user = (UserModel) session.getAttribute("user");

        if (user != null) {
            return getPathFormatter().getPageNameWithPath("user_page");
        } else {
            return "redirect:/user/login";
        }
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new UserModel());
        return getPathFormatter().getPageNameWithPath("login_page");
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserModel userModel, HttpSession session) {
        UserModel authenticatedUser = userService.authenticate(userModel.getEmail(), userModel.getPassword());

        if (authenticatedUser == null) {
            return "redirect:/error";
        } else {
            session.setAttribute("user", authenticatedUser);
            return "redirect:/user";
        }
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerRequest", new UserModel());
        return getPathFormatter().getPageNameWithPath("register_page");
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserModel userModel) {
        UserModel registerUser = userService.registerUser(userModel.getName(), userModel.getLastName(),  userModel.getEmail(), userModel.getPassword());

        if (registerUser == null) {
            return "redirect:/error";
        } else {
            return "redirect:/user/login";
        }
    }
}
