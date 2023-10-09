package sk.uniza.fri.mnamka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavigationController {

    private static final String PAGES_PATH = "pages/%s";
    public static final String ROOT_PAGE = "home";

    public static String getPageWithPath(String pageName) {
        return String.format(PAGES_PATH, pageName);
    }

    @RequestMapping(value={"/"})
    public String root() {
        return NavigationController.getPageWithPath(ROOT_PAGE);
    }

    @GetMapping("/{pageName}")
    public String getPage(@PathVariable String pageName) {
        return NavigationController.getPageWithPath(pageName);
    }
}