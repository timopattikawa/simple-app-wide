package com.widetechnologies.simpleapp.exception;

import com.widetechnologies.simpleapp.exception.type.BadRequestException;
import com.widetechnologies.simpleapp.exception.type.NotFoundDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerException {

    @ExceptionHandler(NotFoundDataException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<GlobalException> NotFoundData(NotFoundDataException e) {
        GlobalException globalError = new GlobalException(e.getMessage(), e.getErrReason(), LocalDateTime.now());

        return new ResponseEntity<>(globalError, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<GlobalException> badRequestException(BadRequestException e) {
        GlobalException globalError = new GlobalException(e.getMessage(), e.getErrReason(), LocalDateTime.now());
        return new ResponseEntity<>(globalError, HttpStatus.BAD_REQUEST);
    }

}
