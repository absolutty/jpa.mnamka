package sk.uniza.fri.mnamka.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.uniza.fri.mnamka.controller.PageController;
import sk.uniza.fri.mnamka.model.FoodModel;
import sk.uniza.fri.mnamka.model.FoodTypeModel;
import sk.uniza.fri.mnamka.service.FoodService;
import sk.uniza.fri.mnamka.service.FoodTypeService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/menu")
public class MenuController extends PageController {

    private final FoodService foodService;
    private final FoodTypeService foodTypeService;

    public MenuController(FoodService foodService, FoodTypeService foodTypeService) {
        this.foodService = foodService;
        this.foodTypeService = foodTypeService;
    }

    @GetMapping
    public String getMenuPage(Model model) {
        List<FoodModel> availableFoods = foodService.getAllFoods();

        Map<FoodTypeModel, List<FoodModel>> categorizedFood = FoodService.categorizeFoodByFoodTypes(foodTypeService.getAllFoodTypes(), availableFoods);

        model.addAttribute("categorizedFood", categorizedFood);

        return getPathFormatter().getPageNameWithPath("menu_page");
    }

}
