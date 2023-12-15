package sk.uniza.fri.mnamka.controller.pages;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sk.uniza.fri.mnamka.helper.Authenticator;
import sk.uniza.fri.mnamka.controller.PageController;
import sk.uniza.fri.mnamka.exception.UserException;
import sk.uniza.fri.mnamka.helper.PathFormatter;
import sk.uniza.fri.mnamka.model.User;
import sk.uniza.fri.mnamka.service.UserService;


@Controller
@RequestMapping(value = "/user")
public class UserController extends PageController {

    private final UserService userService;
    private final Authenticator authenticator;

    public UserController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticator = new Authenticator(authenticationManager);
    }

    @Override
    public PathFormatter getPathFormatter() {
        return new PathFormatter("pages/user/%s");
    }

    @GetMapping
    public String getUserPage(HttpSession session, Model model) {
        if (Authenticator.isUserLoggedIn()) {
            User loggedInUser = (User) session.getAttribute("user");
            model.addAttribute("user", loggedInUser);
            return getPathFormatter().getPageNameWithPath("user_page");
        } else {
            throw new UserException.NotAllowedToAccess();
        }
    }

    @PostMapping("/edit")
    public String doEditLoggedInUser(@ModelAttribute("user")User user, HttpSession session, RedirectAttributes redirectAttributes) {
        if (Authenticator.isUserLoggedIn()) {
            User loggedInUser = (User) session.getAttribute("user");
            user.setId(loggedInUser.getId());
            userService.updateExistingUser(user);
            authenticator.authentificateUserToSession(user, session);

            redirectAttributes.addFlashAttribute("success", "Používateľ úspešne upravený!");
            return "redirect:/user";
        } else {
            throw new UserException.NotAllowedToAccess();
        }
    }


}
