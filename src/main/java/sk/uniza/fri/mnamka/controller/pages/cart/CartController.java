package sk.uniza.fri.mnamka.controller.pages.cart;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sk.uniza.fri.mnamka.controller.PageController;
import sk.uniza.fri.mnamka.model.FoodModel;
import sk.uniza.fri.mnamka.model.OrderedFood;
import sk.uniza.fri.mnamka.service.FoodService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/cart")
public class CartController extends PageController {

    @Autowired private FoodService foodService;

    @GetMapping
    public String getCartPage() {
        return getPathFormatter().getPageNameWithPath("cart_page");
    }

    @PostMapping("/addToCart")
    public ResponseEntity<String> addToCart(@RequestParam Long foodId, @RequestParam Integer quantity, HttpSession session) {
        FoodModel food = foodService.getFoodById(foodId);

        List<OrderedFood> cartContent = (List<OrderedFood>) session.getAttribute("cartContent");
        if (cartContent == null) {
            cartContent = new ArrayList<>();
        }

        OrderedFood orderedFood = new OrderedFood();
        orderedFood.setId(OrderedFood.getNextvalId());
        orderedFood.setFood(food);
        orderedFood.setQuantity(quantity);
        cartContent.add(orderedFood);

        session.setAttribute("cartContent", cartContent);

        return ResponseEntity.ok("{\"status\": \"success\"}");
    }

    @PostMapping("/removeFromCart")
    public ResponseEntity<String> removeFromCart(@RequestParam Long foodId, HttpSession session) {
        List<OrderedFood> cartContent = (List<OrderedFood>) session.getAttribute("cartContent");
        if (cartContent == null) {
            return ResponseEntity.ofNullable("{\"status\": \"failure\"}");
        }

        cartContent.removeIf(orderedFood -> orderedFood.getId().equals(foodId));

        return ResponseEntity.ok("{\"status\": \"success\"}");
    }

}
