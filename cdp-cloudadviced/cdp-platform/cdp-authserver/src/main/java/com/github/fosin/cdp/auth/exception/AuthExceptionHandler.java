package com.github.fosin.cdp.auth.exception;

import com.github.fosin.cdp.mvc.exception.CdpExceptionHandler;
import com.github.fosin.cdp.core.exception.CdpUserOrPassInvalidException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Description
 *
 * @author fosin
 */
@RestControllerAdvice
public class AuthExceptionHandler extends CdpExceptionHandler {
    @ExceptionHandler({CdpUserOrPassInvalidException.class})
    public ResponseEntity<String> cdpUserOrPassInvalidException(CdpUserOrPassInvalidException e) {
        return ResponseEntity.badRequest().body("用户或密码不正确!");
    }
}
