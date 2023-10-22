package sk.uniza.fri.mnamka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.uniza.fri.mnamka.helper.PathFormatter;

@Controller
public class NavigationController {

    public static final String ROOT_PAGE = "home";
    private final PathFormatter pathFormatter = new PathFormatter("pages/%s");

    @RequestMapping(value={"/"})
    public String root() {
        return pathFormatter.getPageNameWithPath(ROOT_PAGE);
    }

    @GetMapping("/{pageName}")
    public String getPage(@PathVariable String pageName) {
        return pathFormatter.getPageNameWithPath(pageName);
    }

}