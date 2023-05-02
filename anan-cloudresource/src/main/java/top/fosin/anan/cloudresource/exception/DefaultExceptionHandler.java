package top.fosin.anan.cloudresource.exception;

import com.fasterxml.jackson.core.JacksonException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.fosin.anan.data.exception.AnanExceptionHandler;
import top.fosin.anan.data.properties.DataProperties;
import top.fosin.anan.data.result.Result;

/**
 * @author fosin
 */
@RestControllerAdvice
public class DefaultExceptionHandler extends AnanExceptionHandler {
    public DefaultExceptionHandler(DataProperties dataProperties) {
        super(dataProperties);
    }

    @ExceptionHandler({JacksonException.class})
    public Result jacksonException(JacksonException e) {
        return badRequestException(e);
    }

}
