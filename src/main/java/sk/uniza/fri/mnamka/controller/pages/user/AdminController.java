package sk.uniza.fri.mnamka.controller.pages.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.uniza.fri.mnamka.controller.PageController;
import sk.uniza.fri.mnamka.exception.UserException;
import sk.uniza.fri.mnamka.helper.Authenticator;
import sk.uniza.fri.mnamka.helper.PathFormatter;
import sk.uniza.fri.mnamka.model.FoodModel;
import sk.uniza.fri.mnamka.model.FoodTypeModel;
import sk.uniza.fri.mnamka.service.FoodService;
import sk.uniza.fri.mnamka.service.FoodTypeService;
import sk.uniza.fri.mnamka.service.UserService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController extends PageController {

    protected UserService userService;
    protected FoodTypeService foodTypeService;
    protected FoodService foodService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    public AdminController(UserService userService, FoodTypeService foodTypeService) {
        this.userService = userService;
        this.foodTypeService = foodTypeService;
    }

    public AdminController(UserService userService, FoodService foodService) {
        this.userService = userService;
        this.foodService = foodService;
    }

    @Autowired
    public AdminController(UserService userService, FoodTypeService foodTypeService, FoodService foodService) {
        this.userService = userService;
        this.foodTypeService = foodTypeService;
        this.foodService = foodService;
    }

    @Override
    public PathFormatter getPathFormatter() {
        return new PathFormatter("pages/user/%s");
    }

    @GetMapping
    public String getAdminPage(Model model) {
        if (Authenticator.isUserLoggedInAdmin()) {
           setFormAttributes(model);

            return getPathFormatter().getPageNameWithPath("admin_page");
        } else {
            throw new UserException.NotAllowedToAccess();
        }
    }

    protected void setFormAttributes(Model model) {
        model.addAttribute("listUserIdentifiers", userService.getUsersIdentifiers());
        model.addAttribute("foodsTypesIdentifiers", foodTypeService.getFoodTypesIdentifiers());

        model.addAttribute("foodTypes", foodTypeService.getAllFoodTypes());

        List<FoodModel> availableFoods = foodService.getAllFoods();
        Map<FoodTypeModel, List<FoodModel>> categorizedFood = FoodService.categorizeFoodByFoodTypes(availableFoods);
        model.addAttribute("categorizedFood", categorizedFood);
    }

}
