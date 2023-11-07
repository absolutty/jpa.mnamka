package sk.uniza.fri.mnamka.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.uniza.fri.mnamka.controller.PageController;
import sk.uniza.fri.mnamka.model.FoodModel;
import sk.uniza.fri.mnamka.service.FoodService;

@Controller
@RequestMapping(value = "/menu")
public class MenuController extends PageController {

    private final FoodService foodService;

    public MenuController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping
    public String getMenuPage() {
        FoodModel cheesePizza = foodService.getFood(3);
        System.out.println(cheesePizza);

        return getPathFormatter().getPageNameWithPath("menu_page");
    }

}
