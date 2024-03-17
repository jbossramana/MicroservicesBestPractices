package demo.hexagon.adapter.web.rest.exception.handler;

public record ApiError(int httpCode, String message) {

    public int getHttpCode() {
        return httpCode;
    }

    public String getMessage() {
        return message;
    }
}
