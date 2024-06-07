package com.progressoft.warehouse.controller;

import com.progressoft.warehouse.exception.InvalidAmountException;
import com.progressoft.warehouse.exception.InvalidDealIdException;
import com.progressoft.warehouse.exception.InvalidISOCodeException;
import com.progressoft.warehouse.exception.InvalidTimeStampException;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class DealControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {InvalidISOCodeException.class, InvalidAmountException.class, InvalidDealIdException.class, InvalidTimeStampException.class})
    protected ResponseEntity<Object> handlePadRequest(RuntimeException ex, WebRequest request) {
        BadRequest bodyOfResponse = new BadRequest();
        bodyOfResponse.setMessage(ex.getMessage());
        bodyOfResponse.setUrl(((ServletWebRequest) request).getRequest().getRequestURI());
        bodyOfResponse.setDateTime(LocalDateTime.now());
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Data
    static class BadRequest {
        String message;
        String url;
        LocalDateTime dateTime;
    }
}

