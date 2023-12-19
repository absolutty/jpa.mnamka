package sk.uniza.fri.mnamka.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EntityException extends RuntimeException{

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Entity you trying to manipulate is not correct!")
    public static class IsNotValid extends RuntimeException {
        public IsNotValid() {
            super();
        }
    }

}
