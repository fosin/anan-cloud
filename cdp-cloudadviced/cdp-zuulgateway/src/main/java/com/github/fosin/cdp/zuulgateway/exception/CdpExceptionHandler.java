package com.github.fosin.cdp.zuulgateway.exception;

import com.github.fosin.cdp.core.exception.CdpControllerException;
import com.github.fosin.cdp.core.exception.CdpServiceException;
import com.github.fosin.cdp.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * Description
 *
 * @author fosin
 */
@RestControllerAdvice
public class CdpExceptionHandler {
    @Value("${spring.profiles.active}")
    private String profile;

    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> exception(Exception e) {
        if (isDev()) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    protected boolean isDev() {
        return profile != null && (profile.contains("dev") || profile.contains("local") || profile.contains("test"));
    }

    @ExceptionHandler({CdpControllerException.class})
    public ResponseEntity<String> cdpControllerException(CdpControllerException e) {
        if (isDev()) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<String> illegalArgumentException(IllegalArgumentException e) {
        if (isDev()) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({CdpServiceException.class})
    public ResponseEntity<String> cdpServiceException(CdpServiceException e) {
        if (isDev()) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<String> methodargumentnotvalidexception(MethodArgumentNotValidException e) {
        if (isDev()) {
            e.printStackTrace();
        }

        BindingResult bindingResult = e.getBindingResult();
        StringBuilder sb = new StringBuilder();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        int count = 0;
        for (ObjectError error : allErrors) {
            if (error != null) {
                String defaultMessage = error.getDefaultMessage();
                if (StringUtil.hasText(defaultMessage)) {
                    sb.append(++count).append("、").append(defaultMessage).append(";").append(System.getProperty("line.separator"));
                }
            }
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(sb.toString());
    }

}
