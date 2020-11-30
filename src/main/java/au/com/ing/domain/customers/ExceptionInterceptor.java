package au.com.ing.domain.customers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

//@ControllerAdvice
//public class ExceptionInterceptor extends ResponseEntityExceptionHandler {
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public final ResponseEntity<Object> handleConstraintViolationExceptions(
//            MethodArgumentNotValidException ex) {
//        String exceptionResponse = String.format("XXXXXXX input parameters: %s\n", ex.getMessage());
//        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
//    }

//}