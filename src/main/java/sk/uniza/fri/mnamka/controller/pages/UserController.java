package sk.uniza.fri.mnamka.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sk.uniza.fri.mnamka.helper.Authenticator;
import sk.uniza.fri.mnamka.controller.PageController;
import sk.uniza.fri.mnamka.exception.UserException;
import sk.uniza.fri.mnamka.helper.PathFormatter;
import sk.uniza.fri.mnamka.model.User;
import sk.uniza.fri.mnamka.service.UserService;

import java.util.List;

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

    @GetMapping("/admin")
    public String getAdminPage(Model model) {
        if (Authenticator.isUserLoggedInAdmin()) {
            List<String> usersIdentifiers = userService.getUsersIdentifiers();
            model.addAttribute("listUserIdentifiers", usersIdentifiers);

            return getPathFormatter().getPageNameWithPath("admin_page");
        } else {
            throw new UserException.NotAllowedToAccess();
        }
    }

    @GetMapping("/admin/editUser")
    public String showEditUserForm(@RequestParam("email") String userEmail, Model model) {
        if (Authenticator.isUserLoggedInAdmin()) {
            List<String> usersIdentifiers = userService.getUsersIdentifiers();
            model.addAttribute("listUserIdentifiers", usersIdentifiers);

            User userToBeEdited = userService.getUserByEmail(userEmail);
            model.addAttribute("userToBeEdited", userToBeEdited);

            return getPathFormatter().getPageNameWithPath("admin_page");
        } else {
            throw new UserException.NotAllowedToAccess();
        }
    }
    @PostMapping("/admin/editUser")
    public String doEditUser(@ModelAttribute User newUser) {
        if (Authenticator.isUserLoggedInAdmin()) {
            userService.updateExistingUser(newUser);
            return "redirect:/user/admin";
        } else {
            throw new UserException.NotAllowedToAccess();
        }
    }

    @GetMapping("/admin/deleteUser")
    public String showdeleteUserForm(@RequestParam("email") String userEmail, Model model) {
        if (Authenticator.isUserLoggedInAdmin()) {
            List<String> usersIdentifiers = userService.getUsersIdentifiers();
            model.addAttribute("listUserIdentifiers", usersIdentifiers);

            User userToBeDeleted = userService.getUserByEmail(userEmail);
            model.addAttribute("userToBeDeleted", userToBeDeleted);

            return getPathFormatter().getPageNameWithPath("admin_page");
        } else {
            throw new UserException.NotAllowedToAccess();
        }
    }
    @PostMapping("/admin/deleteUser")
    public String doDeleteUser(@ModelAttribute User toBeDeleted) {
        if (Authenticator.isUserLoggedInAdmin()) {
            userService.deleteExistingUser(toBeDeleted);
            return "redirect:/user/admin";
        } else {
            throw new UserException.NotAllowedToAccess();
        }
    }

}
