package top.fosin.anan.auth.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.fosin.anan.core.exception.AnanUserOrPassInvalidException;
import top.fosin.anan.model.exception.AnanExceptionHandler;

/**
 * @author fosin
 */
@RestControllerAdvice
public class AuthExceptionHandler extends AnanExceptionHandler {
    @ExceptionHandler({AnanUserOrPassInvalidException.class})
    public ResponseEntity<String> ananUserOrPassInvalidException(AnanUserOrPassInvalidException e) {
        return ResponseEntity.badRequest().body("用户或密码不正确!");
    }
}
