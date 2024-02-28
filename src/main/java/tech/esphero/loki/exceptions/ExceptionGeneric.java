package tech.esphero.loki.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import tech.esphero.loki.enums.ErrorCode;

@Data
public class ExceptionGeneric extends RuntimeException {

    private HttpStatus status;
    private ErrorCode code;

    private String type;

    public ExceptionGeneric(String type, String message, HttpStatus status, ErrorCode code) {
        super(message);
        this.status = status;
        this.code = code;
        this.type = type;
    }

}
