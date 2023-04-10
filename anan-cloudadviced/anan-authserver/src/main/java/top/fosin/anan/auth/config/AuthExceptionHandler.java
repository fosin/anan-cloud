package top.fosin.anan.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.fosin.anan.cloudresource.exception.DefaultExceptionHandler;
import top.fosin.anan.core.result.ResultCode;
import top.fosin.anan.data.properties.AnanDataProperties;
import top.fosin.anan.data.result.Result;
import top.fosin.anan.data.result.ResultUtils;

/**
 * @author fosin
 */
@RestControllerAdvice
@Slf4j
public class AuthExceptionHandler extends DefaultExceptionHandler {
    public AuthExceptionHandler(AnanDataProperties ananDataProperties) {
        super(ananDataProperties);
    }

    @ExceptionHandler({AuthenticationException.class})
    public Result authenticationException(AuthenticationException e) {
        String message = getRealCause(e);
        log.info(message);
        if (log.isDebugEnabled()) {
            e.printStackTrace();
        }
        return ResultUtils.failure(ResultCode.FAILURE.getCode(), message);
    }
}
