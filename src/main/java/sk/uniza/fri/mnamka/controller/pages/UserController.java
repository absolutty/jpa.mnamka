package sk.uniza.fri.mnamka.controller.pages;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.uniza.fri.mnamka.controller.PageController;
import sk.uniza.fri.mnamka.helper.PathFormatter;

@Controller
@RequestMapping(value = "/user")
public class UserController extends PageController {

    @Override
    public PathFormatter getPathFormatter() {
        return new PathFormatter("pages/user/%s");
    }

    @GetMapping
    public String getUserPage(HttpSession session) {
        return getPathFormatter().getPageNameWithPath("user_page");
    }

}
