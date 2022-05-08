package top.fosin.anan.cloudresource.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.InvalidNullException;
import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.fosin.anan.model.exception.AnanExceptionHandler;
import top.fosin.anan.model.properties.AnanDataProperties;

/**
 * @author fosin
 */
@RestControllerAdvice
@Slf4j
public class PlatformExceptionHandler extends AnanExceptionHandler {
    public PlatformExceptionHandler(AnanDataProperties ananDataProperties) {
        super(ananDataProperties);
    }

    @ExceptionHandler({InvalidFormatException.class, InvalidTypeIdException.class, JsonMappingException.class,
            InvalidNullException.class,})
    public ResponseEntity<String> jsonMappingException(JsonMappingException e) {
        String message = e.getMessage();
        log.info(e.getLocalizedMessage());
        log.info(e.getPathReference());
        log.info(e.getOriginalMessage());
        log.info(String.valueOf(e.getPath()));
        log.info(e.getLocation().toString());
        log.info(message);
        return ResponseEntity.badRequest().body(message);
    }

}
