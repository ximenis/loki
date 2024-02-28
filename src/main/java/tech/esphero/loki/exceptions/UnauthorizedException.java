package tech.esphero.loki.exceptions;

import org.springframework.http.HttpStatus;
import tech.esphero.loki.enums.ErrorCode;

public class UnauthorizedException extends ExceptionGeneric {
    public UnauthorizedException(String type, String message, HttpStatus status, ErrorCode code) {
        super(type, message, status, code);
    }
}
