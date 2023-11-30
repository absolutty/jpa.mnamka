package sk.uniza.fri.mnamka.controller.pages.user;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sk.uniza.fri.mnamka.exception.UserException;
import sk.uniza.fri.mnamka.helper.Authenticator;
import sk.uniza.fri.mnamka.model.FoodTypeModel;

@Controller
@RequestMapping("/admin/foodtypes")
public class EditFoodTypeController extends AdminController {

    @GetMapping("/edit")
    public String showEditFoodTypeForm(@RequestParam("name") String name, Model model) {
        if (Authenticator.isUserLoggedInAdmin()) {
            initializeCommonFormAttributes(model);

            FoodTypeModel foodTypeToBeEdited = foodTypeService.getFoodTypeByName(name);
            model.addAttribute("foodTypeToBeEdited", foodTypeToBeEdited);

            return getPathFormatter().getPageNameWithPath("admin_page");
        } else {
            throw new UserException.NotAllowedToAccess();
        }
    }
    @PostMapping("/edit")
    public String doEditUser(@ModelAttribute FoodTypeModel newFoodType, RedirectAttributes redirectAttributes) {
        if (Authenticator.isUserLoggedInAdmin()) {
            foodTypeService.updateExistingUser(newFoodType);

            redirectAttributes.addFlashAttribute("success", "Typ jedla uspešne upravená!");
            return "redirect:/admin";
        } else {
            throw new UserException.NotAllowedToAccess();
        }
    }

    @GetMapping("/delete")
    public String showdeleteFoodTypeForm(@RequestParam("name") String name, Model model) {
        if (Authenticator.isUserLoggedInAdmin()) {
            initializeCommonFormAttributes(model);

            FoodTypeModel foodTypeToBeDeleted = foodTypeService.getFoodTypeByName(name);
            model.addAttribute("foodTypeToBeDeleted", foodTypeToBeDeleted);

            return getPathFormatter().getPageNameWithPath("admin_page");
        } else {
            throw new UserException.NotAllowedToAccess();
        }
    }
    @PostMapping("/delete")
    public String doDeleteFoodType(@ModelAttribute FoodTypeModel toBeDeleted, RedirectAttributes redirectAttributes) {
        if (Authenticator.isUserLoggedInAdmin()) {
            try {
                foodTypeService.deleteExistingFoodType(toBeDeleted);
                redirectAttributes.addFlashAttribute("success", "Kategoria jedla uspesne vymazana!");
            } catch (DataIntegrityViolationException e) {
                redirectAttributes.addFlashAttribute("failure", e.getMessage());
            }
            return "redirect:/admin";

        } else {
            throw new UserException.NotAllowedToAccess();
        }
    }

}
