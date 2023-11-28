package sk.uniza.fri.mnamka.controller.pages.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.uniza.fri.mnamka.controller.PageController;
import sk.uniza.fri.mnamka.exception.UserException;
import sk.uniza.fri.mnamka.helper.Authenticator;
import sk.uniza.fri.mnamka.helper.PathFormatter;
import sk.uniza.fri.mnamka.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController extends PageController {

    protected final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public PathFormatter getPathFormatter() {
        return new PathFormatter("pages/user/%s");
    }

    @GetMapping
    public String getAdminPage(Model model) {
        if (Authenticator.isUserLoggedInAdmin()) {
            List<String> usersIdentifiers = userService.getUsersIdentifiers();
            model.addAttribute("listUserIdentifiers", usersIdentifiers);

            return getPathFormatter().getPageNameWithPath("admin_page");
        } else {
            throw new UserException.NotAllowedToAccess();
        }
    }

}
