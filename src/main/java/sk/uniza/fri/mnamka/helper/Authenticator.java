package sk.uniza.fri.mnamka.helper;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import sk.uniza.fri.mnamka.model.User;

public class Authenticator {

    private final AuthenticationManager authenticationManager;
    public Authenticator(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public void authentificateUserToSession(User user, HttpSession session) {
        Authentication newAuthentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        );

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(newAuthentication);

        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
        session.setAttribute("user", user);
    }

    public static boolean isUserLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return  (authentication != null) &&
                (authentication.isAuthenticated()) &&
                (!authentication.getName().equals("anonymousUser"));
    }

    public static boolean isUserLoggedInAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return  isUserLoggedIn() &&
                authentication.getAuthorities().stream().anyMatch(authority -> "ROLE_ADMIN".equals(authority.getAuthority()));
    }

}
