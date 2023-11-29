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
import sk.uniza.fri.mnamka.service.FoodService;
import sk.uniza.fri.mnamka.service.FoodTypeService;
import sk.uniza.fri.mnamka.service.UserService;

import java.util.List;

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
            model.addAttribute("listUserIdentifiers", userService.getUsersIdentifiers());
            model.addAttribute("foodsTypesIdentifiers", foodTypeService.getFoodTypesIdentifiers());

            return getPathFormatter().getPageNameWithPath("admin_page");
        } else {
            throw new UserException.NotAllowedToAccess();
        }
    }

}
