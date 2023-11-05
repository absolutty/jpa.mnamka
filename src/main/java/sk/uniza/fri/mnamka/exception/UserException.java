package sk.uniza.fri.mnamka.exception;

public class UserException {

    public static class InvalidCredentialsException extends RuntimeException {
        public InvalidCredentialsException(String email, String password) {
            super(String.format("The login credentials provided are incorrect. [%s, %s]", email, password));
        }
    }

}
