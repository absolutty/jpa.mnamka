package sk.uniza.fri.mnamka.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.uniza.fri.mnamka.helper.PathFormatter;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    private final PathFormatter pathFormatter = new PathFormatter("pages/error/%s");

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            model.addAttribute("errorCode", statusCode);

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("errorImage", "/images/error404.png");
                model.addAttribute("errorMessage", "Ups, stránka nebola nájdená!");
            } else if(statusCode == HttpStatus.UNAUTHORIZED.value()) {
                model.addAttribute("errorImage", "/images/error403.png");
                model.addAttribute("errorMessage", "Ups, sem nemáš prístup!");
            }
        }
        return pathFormatter.getPageNameWithPath("error-page");
    }

}
