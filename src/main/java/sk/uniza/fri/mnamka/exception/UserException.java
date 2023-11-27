package sk.uniza.fri.mnamka.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class UserException extends RuntimeException{

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Unauthorized access by user")
    public static class NotAllowedToAccess extends RuntimeException {
        public NotAllowedToAccess() {
            super();
        }
    }

}
