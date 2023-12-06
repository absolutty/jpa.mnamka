package sk.uniza.fri.mnamka.controller.pages.cart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.uniza.fri.mnamka.controller.PageController;

@Controller
@RequestMapping(value = "/cart")
public class CartController extends PageController {

    @GetMapping
    public String getCartPage() {
        return getPathFormatter().getPageNameWithPath("cart_page");
    }

}
