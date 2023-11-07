package sk.uniza.fri.mnamka.exception;

public class FoodException {

    public static class IdNotFoundException extends RuntimeException {
        public IdNotFoundException(int id) {
            super(String.format("The id of food you have provided is invalid. [%s]", id));
        }
    }

}
