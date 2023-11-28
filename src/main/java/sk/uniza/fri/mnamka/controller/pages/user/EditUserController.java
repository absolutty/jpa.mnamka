package sk.uniza.fri.mnamka.controller.pages.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sk.uniza.fri.mnamka.exception.UserException;
import sk.uniza.fri.mnamka.helper.Authenticator;
import sk.uniza.fri.mnamka.model.User;
import sk.uniza.fri.mnamka.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class EditUserController extends AdminController {

    public EditUserController(UserService userService) {
        super(userService);
    }

    @GetMapping("/edit")
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
    @PostMapping("/edit")
    public String doEditUser(@ModelAttribute User newUser) {
        if (Authenticator.isUserLoggedInAdmin()) {
            userService.updateExistingUser(newUser);
            return "redirect:/admin";
        } else {
            throw new UserException.NotAllowedToAccess();
        }
    }

    @GetMapping("/delete")
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
    @PostMapping("/delete")
    public String doDeleteUser(@ModelAttribute User toBeDeleted) {
        if (Authenticator.isUserLoggedInAdmin()) {
            userService.deleteExistingUser(toBeDeleted);
            return "redirect:/admin";
        } else {
            throw new UserException.NotAllowedToAccess();
        }
    }

}
