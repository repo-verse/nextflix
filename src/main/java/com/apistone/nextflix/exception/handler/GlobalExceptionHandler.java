package com.apistone.nextflix.exception.handler;

import com.apistone.nextflix.response.Response;
import com.apistone.nextflix.constant.StringConstant;
import com.apistone.nextflix.exception.ZipcodeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Project: nextflix
 * Package: com.apistone.nextflix.exception.handler
 * <p>
 * Created by: Rahul Kumar Maurya
 * Date: 1/21/2023
 * Time: 12:30 PM
 * <p>
 * Use: Global exception handler for whole application.
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    private Response getErrorResponse(String message, WebRequest wr, int status){
        return new Response(
                message,
                status,
                System.currentTimeMillis(),
                wr.getDescription(false).split("=")[1]
        );
    }

    /**
     * Exception handler for incorrect email or password.
     * @param ex Object of {@link  com.apistone.nextflix.exception.ZipcodeNotFoundException }.
     * @param wr Object of {@link  org.springframework.web.context.request.WebRequest }.
     * @return {@link org.springframework.http.ResponseEntity} with {@link com.apistone.nextflix.response.Response} as object
     * and http status code.
     */
    @ExceptionHandler(ZipcodeNotFoundException.class )
    public ResponseEntity<Response> handleBadCredentialsException(ZipcodeNotFoundException ex, WebRequest wr){
        ex.printStackTrace();
        Response errorResponse = getErrorResponse(ex.getMessage(), wr, HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    /**
     * Global exception handler for all the types of exception.
     * @param ex Object of {@link  java.lang.Exception }.
     * @param wr Object of {@link  org.springframework.web.context.request.WebRequest }.
     * @return {@link org.springframework.http.ResponseEntity} with {@link com.apistone.nextflix.response.Response} as object
     * and http status code.
     */
    @ExceptionHandler(Exception.class )
    public ResponseEntity<Response> handleGlobalException(Exception ex, WebRequest wr){
        ex.printStackTrace();
        Response errorResponse = getErrorResponse(StringConstant.GENERIC_EXCEPTION_MESSAGE, wr, HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
