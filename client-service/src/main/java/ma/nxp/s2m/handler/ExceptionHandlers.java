package ma.nxp.s2m.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ExceptionHandlers {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandlers.class);


    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseError methodArgumentNotValidExceptionHandler(AccessDeniedException e) {
        return new ResponseError("ACCESS.DENIED",e.getMessage());
    }

    public static class ResponseError {
        private final String code;
        private final String message;

        private ResponseError(String code, String message) {
            super();
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

    }
}
