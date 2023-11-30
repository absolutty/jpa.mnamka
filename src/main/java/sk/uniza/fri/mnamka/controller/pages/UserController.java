package sk.uniza.fri.mnamka.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sk.uniza.fri.mnamka.helper.Authenticator;
import sk.uniza.fri.mnamka.controller.PageController;
import sk.uniza.fri.mnamka.exception.UserException;
import sk.uniza.fri.mnamka.helper.PathFormatter;
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
    public String getUserPage() {
        if (Authenticator.isUserLoggedIn()) {
            return getPathFormatter().getPageNameWithPath("user_page");
        } else {
            throw new UserException.NotAllowedToAccess();
        }
    }

}
