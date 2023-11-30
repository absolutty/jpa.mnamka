package sk.uniza.fri.mnamka.controller.pages.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sk.uniza.fri.mnamka.exception.UserException;
import sk.uniza.fri.mnamka.helper.Authenticator;
import sk.uniza.fri.mnamka.model.User;

@Controller
@RequestMapping("/admin/users")
public class EditUserController extends AdminController {

    @GetMapping("/edit")
    public String showEditUserForm(@RequestParam("email") String userEmail, Model model) {
        if (Authenticator.isUserLoggedInAdmin()) {
            initializeCommonFormAttributes(model);

            User userToBeEdited = userService.getUserByEmail(userEmail);
            model.addAttribute("userToBeEdited", userToBeEdited);

            return getPathFormatter().getPageNameWithPath("admin_page");
        } else {
            throw new UserException.NotAllowedToAccess();
        }
    }
    @PostMapping("/edit")
    public String doEditUser(@ModelAttribute User newUser, RedirectAttributes redirectAttributes) {
        if (Authenticator.isUserLoggedInAdmin()) {
            userService.updateExistingUser(newUser);

            redirectAttributes.addFlashAttribute("success", "Používateľ úspešne upravený!");
            return "redirect:/admin";
        } else {
            throw new UserException.NotAllowedToAccess();
        }
    }

    @GetMapping("/delete")
    public String showdeleteUserForm(@RequestParam("email") String userEmail, Model model) {
        if (Authenticator.isUserLoggedInAdmin()) {
            initializeCommonFormAttributes(model);

            User userToBeDeleted = userService.getUserByEmail(userEmail);
            model.addAttribute("userToBeDeleted", userToBeDeleted);

            return getPathFormatter().getPageNameWithPath("admin_page");
        } else {
            throw new UserException.NotAllowedToAccess();
        }
    }
    @PostMapping("/delete")
    public String doDeleteUser(@ModelAttribute User toBeDeleted, RedirectAttributes redirectAttributes) {
        if (Authenticator.isUserLoggedInAdmin()) {
            userService.deleteExistingUser(toBeDeleted);

            redirectAttributes.addFlashAttribute("success", "Používateľ úspešne odstránený!");
            return "redirect:/admin";
        } else {
            throw new UserException.NotAllowedToAccess();
        }
    }

}
