package sk.uniza.fri.mnamka.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.uniza.fri.mnamka.controller.PageController;

@Controller
@RequestMapping(value = "/menu")
public class MenuController extends PageController {

    @GetMapping
    public String getMenuPage() {
        return getPathFormatter().getPageNameWithPath("menu_page");
    }

}
