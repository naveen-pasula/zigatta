package com.zigatta.siriusXM.zigattaVehicleApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceException extends RuntimeException {

    private HttpStatus statusCode;
    private String errorMessage;

    public ResourceException(HttpStatus statusCode, String errorMessage) {
        super(String.format("%s", errorMessage));
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

