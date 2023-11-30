package sk.uniza.fri.mnamka.controller.pages.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sk.uniza.fri.mnamka.exception.UserException;
import sk.uniza.fri.mnamka.helper.Authenticator;
import sk.uniza.fri.mnamka.model.FoodModel;

@Controller
@RequestMapping("/admin/foods")
public class EditFoodController extends AdminController {

    @GetMapping("/edit")
    public String showEditFoodForm(@RequestParam("id") Long id, Model model) {
        if (Authenticator.isUserLoggedInAdmin()) {
            setFormAttributes(model);

            FoodModel foodToBeEdited = foodService.getFoodById(id);
            model.addAttribute("foodToBeEdited", foodToBeEdited);

            return getPathFormatter().getPageNameWithPath("admin_page");
        } else {
            throw new UserException.NotAllowedToAccess();
        }
    }
    @PostMapping("/edit")
    public String doEditFood(@ModelAttribute FoodModel newFood, Model model, RedirectAttributes redirectAttributes) {
        if (Authenticator.isUserLoggedInAdmin()) {
            setFormAttributes(model);

            foodService.updateExistingFood(newFood);

            redirectAttributes.addFlashAttribute("success", "Jedlo uspešne upravené!");
            return "redirect:/admin";
        } else {
            throw new UserException.NotAllowedToAccess();
        }
    }

    @GetMapping("/delete")
    public String showdeleteFoodForm(@RequestParam("id") Long foodId, Model model) {
        if (Authenticator.isUserLoggedInAdmin()) {
            setFormAttributes(model);

            FoodModel foodToBeDeleted = foodService.getFoodById(foodId);
            model.addAttribute("foodToBeDeleted", foodToBeDeleted);

            return getPathFormatter().getPageNameWithPath("admin_page");
        } else {
            throw new UserException.NotAllowedToAccess();
        }
    }
    @PostMapping("/delete")
    public String doDeleteFood(@ModelAttribute FoodModel toBeDeleted, RedirectAttributes redirectAttributes) {
        if (Authenticator.isUserLoggedInAdmin()) {
            foodService.deleteExistingFood(toBeDeleted);
            redirectAttributes.addFlashAttribute("success", "Jedlo uspesne vymazane!");

            return "redirect:/admin";
        } else {
            throw new UserException.NotAllowedToAccess();
        }
    }

}
