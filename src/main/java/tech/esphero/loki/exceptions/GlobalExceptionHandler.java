package tech.esphero.loki.exceptions;

import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ExceptionGeneric.class)
    ProblemDetail handleException(ExceptionGeneric e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(e.getStatus(), e.getLocalizedMessage());
        problemDetail.setType(URI.create(e.getType()));
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("code", e.getCode());
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

}
