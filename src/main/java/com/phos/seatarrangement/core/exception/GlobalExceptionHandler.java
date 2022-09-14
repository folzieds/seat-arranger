package com.phos.seatarrangement.core.exception;

import com.phos.seatarrangement.core.event.exception.EventNotFoundException;
import com.phos.seatarrangement.core.guest.exception.GuestNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public PlatformDataIntegrityException handlePlatformDataIntegrityException(PlatformDataIntegrityException ex){
        return ex;
    }

    @ExceptionHandler(GuestNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public GuestNotFoundException handleGuestNotFoundException(GuestNotFoundException ex){
        return ex;
    }

    @ExceptionHandler(EventNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public EventNotFoundException handleEventNotFoundException(EventNotFoundException ex){
        return ex;
    }
}
