package com.labillusion.core.platform.web.rest;

import com.labillusion.core.platform.exception.ResourceNotFoundException;
import com.labillusion.core.platform.exception.UserAuthorizationException;
import com.labillusion.core.platform.web.rest.exception.ErrorResponse;
import com.labillusion.core.platform.web.rest.exception.ErrorResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by greg.chen on 14-10-13.
 */
@RestController
public class RESTController {

    @Autowired
    protected ErrorResponseBuilder errorResponseBuilder;

    @ExceptionHandler(value = Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse error(Throwable e) {
        return errorResponseBuilder.createErrorResponse(e);
    }

    @ExceptionHandler(value=ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse resourceNotFound(ResourceNotFoundException ex){
        return errorResponseBuilder.createErrorResponse(ex);
    }

    @ExceptionHandler(value=UserAuthorizationException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse unauthorized(UserAuthorizationException ex){
        return errorResponseBuilder.createErrorResponse(ex);
    }


}
