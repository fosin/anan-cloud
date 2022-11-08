package top.fosin.anan.cloudresource.exception;

import com.fasterxml.jackson.core.JacksonException;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.fosin.anan.data.exception.AnanExceptionHandler;
import top.fosin.anan.data.properties.AnanDataProperties;
import top.fosin.anan.data.result.Result;

/**
 * @author fosin
 */
@RestControllerAdvice
@Order(9999)
public class PlatformExceptionHandler extends AnanExceptionHandler {
    public PlatformExceptionHandler(AnanDataProperties ananDataProperties) {
        super(ananDataProperties);
    }

    @ExceptionHandler({JacksonException.class})
    public Result jacksonException(JacksonException e) {
        return badRequestException(e);
    }

}
