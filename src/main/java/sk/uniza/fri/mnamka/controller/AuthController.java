package sk.uniza.fri.mnamka.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sk.uniza.fri.mnamka.model.User;
import sk.uniza.fri.mnamka.service.UserService;


@Controller
@RequestMapping("")
public class AuthController {

    private final UserService userService;
    private final Authenticator authenticator;

    public AuthController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticator = new Authenticator(authenticationManager);
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @PostMapping("/do-login")
    public String performLogin(HttpServletRequest request, @ModelAttribute User user) {
        try {
            User authenticated = userService.authenticateUser(user);
            authenticator.authentificateUserToSession(authenticated, request.getSession(true));
            return "redirect:/home";
        } catch (BadCredentialsException ex) {
            return "redirect:/login-error";
        }
    }

    @RequestMapping("/login-error")
    public String loginError(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("failure", "Nesprávny email alebo heslo! Skúste znovu prosím.");
        return "redirect:/login";
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(null);
        return "redirect:/home";
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(HttpServletRequest request, HttpServletResponse response, Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String createNewUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user")User user){
        user.setRole("USER");
        try {
            User newUser = userService.createUser(user);
            authenticator.authentificateUserToSession(newUser, request.getSession(true));

            return "redirect:/";

        } catch (BadCredentialsException e){
            return "redirect:/registerEmailAlreadyExists";
        }
    }


    @RequestMapping("/registerEmailAlreadyExists")
    public String registerError(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("failure", "Tento email uz existuje!");
        return "redirect:/register";
    }

}
