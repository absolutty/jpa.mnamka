package sk.uniza.fri.mnamka.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.uniza.fri.mnamka.controller.PageController;

@Controller
@RequestMapping(value = "/home")
public class HomeController extends PageController {

    @GetMapping
    public String getHomePage() {
        return getPathFormatter().getPageNameWithPath("home");
    }

}
