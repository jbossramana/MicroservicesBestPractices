package demo.hexagon.adapter.web.rest.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import demo.hexagon.domain.exception.UserNameAlreadyTaken;
import demo.hexagon.domain.exception.UserNotFound;

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(value = UserNameAlreadyTaken.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ApiError handleException(final UserNameAlreadyTaken userNameAlreadyTaken) {
        return new ApiError(HttpStatus.BAD_REQUEST.value(), userNameAlreadyTaken.getMessage());
    }

    @ExceptionHandler(value = UserNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    ApiError handleException(final UserNotFound UserNotFound) {
        return new ApiError(HttpStatus.NOT_FOUND.value(), UserNotFound.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ApiError handleException(final MethodArgumentNotValidException methodArgumentNotValidException) {
        return new ApiError(HttpStatus.BAD_REQUEST.value(), methodArgumentNotValidException.getMessage());
    }
}
