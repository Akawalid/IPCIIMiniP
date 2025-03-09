package Model.Exceptions;

public class InvalidCoordinates extends Exception {
    //Exception launched by model, when the coordinates are invalid, After that, the controller should handle the generated exception
    public InvalidCoordinates(String message) {
        super(message);
    }
}
