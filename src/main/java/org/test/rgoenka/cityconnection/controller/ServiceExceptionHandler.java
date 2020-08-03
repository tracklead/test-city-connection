package org.test.rgoenka.cityconnection.controller;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class ServiceExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> handleAllException(Exception exception, WebRequest request) {
        return new ResponseEntity<>("no", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
