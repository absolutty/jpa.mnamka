package sk.uniza.fri.mnamka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;
import sk.uniza.fri.mnamka.helper.PathFormatter;

@Controller
public class PageController {

    private final PathFormatter defaultPathFormatter = new PathFormatter("pages/%s");

    public PathFormatter getPathFormatter() {
        return defaultPathFormatter;
    }

    @GetMapping("/")
    public RedirectView root() {
        return new RedirectView("/home");
    }

}
