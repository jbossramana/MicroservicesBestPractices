package demo.hexagon.domain.exception;

public class UserNameAlreadyTaken extends RuntimeException {
    public UserNameAlreadyTaken(final String message) {
        super(message);
    }
}
