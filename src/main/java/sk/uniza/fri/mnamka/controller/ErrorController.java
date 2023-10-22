package sk.uniza.fri.mnamka.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sk.uniza.fri.mnamka.helper.PathFormatter;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    private final PathFormatter pathFormatter = new PathFormatter("pages/error/%s");

    @RequestMapping("/error")
    public String handleError() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        if (response != null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return pathFormatter.getPageNameWithPath("page404");
    }
}
