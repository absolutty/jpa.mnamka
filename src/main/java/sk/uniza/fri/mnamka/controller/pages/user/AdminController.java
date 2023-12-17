package sk.uniza.fri.mnamka.controller.pages.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sk.uniza.fri.mnamka.controller.PageController;
import sk.uniza.fri.mnamka.exception.UserException;
import sk.uniza.fri.mnamka.helper.Authenticator;
import sk.uniza.fri.mnamka.helper.PathFormatter;
import sk.uniza.fri.mnamka.model.FoodModel;
import sk.uniza.fri.mnamka.model.FoodTypeModel;
import sk.uniza.fri.mnamka.service.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController extends PageController {

    @Autowired protected UserService userService;
    @Autowired protected FoodTypeService foodTypeService;
    @Autowired protected FoodService foodService;
    @Autowired protected OrderService orderService;
    @Autowired protected OrderedFoodService orderedFoodService;

    @Override
    public PathFormatter getPathFormatter() {
        return new PathFormatter("pages/user/%s");
    }

    @GetMapping
    public String getAdminPage(Model model) {
        if (Authenticator.isUserLoggedInAdmin()) {
            initializeCommonFormAttributes(model);

            return getPathFormatter().getPageNameWithPath("admin_page");
        } else {
            throw new UserException.NotAllowedToAccess();
        }
    }

    @GetMapping("/processOrders")
    public String getProcessOrderPage(Model model) {
        if (Authenticator.isUserLoggedInAdmin()) {
            model.addAttribute("orders", OrderService.groupByOrder(orderService.getAllOrders(), orderedFoodService.getAllOrderedFoods()));
            return getPathFormatter().getPageNameWithPath("process_order_page");
        } else {
            throw new UserException.NotAllowedToAccess();
        }
    }

    @GetMapping("/getActualDate")
    public @ResponseBody String getActualDate() {
        Date currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(currentDate);
    }

    @GetMapping("/getActualTime")
    public @ResponseBody String getActualTime() {
        Date currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(currentDate);
    }

    protected void initializeCommonFormAttributes(Model model) {
        model.addAttribute("listUserIdentifiers", userService.getUsersIdentifiers());
        model.addAttribute("foodsTypesIdentifiers", foodTypeService.getFoodTypesIdentifiers());
        model.addAttribute("foodTypes", foodTypeService.getAllFoodTypes());

        Map<FoodTypeModel, List<FoodModel>> categorizedFood = FoodService.categorizeFoodByFoodTypes(
                foodTypeService.getAllFoodTypes(), foodService.getAllFoods()
        );
        model.addAttribute("categorizedFood", categorizedFood);
    }

}
