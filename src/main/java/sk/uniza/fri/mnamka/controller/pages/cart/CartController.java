package sk.uniza.fri.mnamka.controller.pages.cart;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sk.uniza.fri.mnamka.controller.PageController;
import sk.uniza.fri.mnamka.helper.Authenticator;
import sk.uniza.fri.mnamka.model.FoodModel;
import sk.uniza.fri.mnamka.model.Order;
import sk.uniza.fri.mnamka.model.OrderedFood;
import sk.uniza.fri.mnamka.model.User;
import sk.uniza.fri.mnamka.service.FoodService;
import sk.uniza.fri.mnamka.service.OrderService;
import sk.uniza.fri.mnamka.service.OrderedFoodService;
import sk.uniza.fri.mnamka.service.UserService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping(value = "/cart")
public class CartController extends PageController {

    @Autowired private FoodService foodService;
    @Autowired private UserService userService;
    @Autowired private OrderService orderService;
    @Autowired private OrderedFoodService orderedFoodService;

    @GetMapping
    public String getCartPage(HttpSession session, Model model) {
        User user;

        if (Authenticator.isUserLoggedIn()) {
            user = (User) session.getAttribute("user");
        } else {
            user = new User(true);

        }

        model.addAttribute("user", user);
        return getPathFormatter().getPageNameWithPath("cart_page");
    }

    @PostMapping("/addToCart")
    public ResponseEntity<String> addToCart(@RequestParam Long foodId, @RequestParam Integer quantity, HttpSession session) {
        FoodModel food = foodService.getFoodById(foodId);

        List<OrderedFood> cartContent = (List<OrderedFood>) session.getAttribute("cartContent");
        if (cartContent == null) {
            cartContent = new ArrayList<>();
        }

        OrderedFood orderedFood = new OrderedFood(food, quantity);
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

        if (cartContent.size() == 0) {
            session.removeAttribute("cartContent");
        }

        return ResponseEntity.ok("{\"status\": \"success\"}");
    }

    @PostMapping("/changeQuantity")
    public ResponseEntity<String> changeQuantity(@RequestParam Long foodId, @RequestParam  Integer quantity,  HttpSession session) {
        List<OrderedFood> cartContent = (List<OrderedFood>) session.getAttribute("cartContent");
        if (cartContent == null) {
            return ResponseEntity.ofNullable("{\"status\": \"failure\"}");
        }

        OrderedFood orderedFood = cartContent.stream().filter(food -> food.getId().equals(foodId)).findFirst().orElse(null);
        if (orderedFood == null) {
            return ResponseEntity.ofNullable("{\"status\": \"failure\"}");
        }
        orderedFood.setQuantity(quantity);

        return ResponseEntity.ok("{\"status\": \"success\"}");
    }

    @PostMapping("/createOrder")
    public String doCreateOrder(@ModelAttribute("user") User user, HttpSession session) {
        User userFromDatabase;

        if (Authenticator.isUserLoggedIn()) {
            User userInSession = (User)session.getAttribute("user");
            userFromDatabase = userService.getUserByEmail(userInSession.getEmail());
        } else {
            userFromDatabase = null;
        }

        List<OrderedFood> cartContent = (List<OrderedFood>) session.getAttribute("cartContent");

        Order createdOrder = orderService.createOrder(new Order(userFromDatabase, Calendar.getInstance().getTime(), Order.OrderState.PENDING));
        for (OrderedFood orderedFood : cartContent) {
            orderedFood.setOrder(createdOrder);
            orderedFoodService.createOrderFood(orderedFood);
        }

        cartContent.clear();
        session.removeAttribute("cartContent");

        return "redirect:/home";
    }
}
