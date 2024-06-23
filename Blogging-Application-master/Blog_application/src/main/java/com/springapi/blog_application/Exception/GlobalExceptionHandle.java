package com.springapi.blog_application.Exception;

import com.springapi.blog_application.ApiResponse.Apiresponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandle {
     @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Apiresponse> resourceNotFoundException(ResourceNotFoundException e) {
         String message = e.getMessage();
         Apiresponse apiresponse = new Apiresponse(message,false);
         return new ResponseEntity<Apiresponse>(apiresponse, HttpStatus.NOT_FOUND);
     }
}
