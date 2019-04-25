package com.github.fosin.anan.auth.exception;

import com.github.fosin.anan.mvc.exception.AnanExceptionHandler;
import com.github.fosin.anan.core.exception.AnanUserOrPassInvalidException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Description
 *
 * @author fosin
 */
@RestControllerAdvice
public class AuthExceptionHandler extends AnanExceptionHandler {
    @ExceptionHandler({AnanUserOrPassInvalidException.class})
    public ResponseEntity<String> ananUserOrPassInvalidException(AnanUserOrPassInvalidException e) {
        return ResponseEntity.badRequest().body("用户或密码不正确!");
    }
}
