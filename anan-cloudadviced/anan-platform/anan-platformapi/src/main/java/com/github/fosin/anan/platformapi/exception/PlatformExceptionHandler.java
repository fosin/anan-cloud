package com.github.fosin.anan.platformapi.exception;

import com.github.fosin.anan.mvc.exception.AnanExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * Description
 *
 * @author fosin
 */
@RestControllerAdvice
public class PlatformExceptionHandler extends AnanExceptionHandler {
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<String> validationException(ConstraintViolationException e) {
        if (isDev()) {
            e.printStackTrace();
        }
        String lineSeparator = System.getProperty("line.separator");
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation constraintViolation : constraintViolations) {
            sb.append(constraintViolation.getPropertyPath()).append(":").append(constraintViolation.getMessage()).append(";").append(lineSeparator);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(sb.toString());
    }
}
