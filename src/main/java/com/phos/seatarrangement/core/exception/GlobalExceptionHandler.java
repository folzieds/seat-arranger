package com.phos.seatarrangement.core.exception;

import com.phos.seatarrangement.core.document.exception.DocumentNotFoundException;
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
    public GlobalErrorResponse handlePlatformDataIntegrityException(PlatformDataIntegrityException ex){
        return new GlobalErrorResponse(ex.getDefaultGlobalCode(), ex.getDefaultUserMessage());
    }

    @ExceptionHandler(GuestNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public GlobalErrorResponse handleGuestNotFoundException(GuestNotFoundException ex){
        return new GlobalErrorResponse(ex.getDefaultGlobalCode(), ex.getDefaultUserMessage());
    }

    @ExceptionHandler(EventNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public GlobalErrorResponse handleEventNotFoundException(EventNotFoundException ex){
        return new GlobalErrorResponse(ex.getDefaultGlobalCode(), ex.getDefaultUserMessage());
    }

    @ExceptionHandler(DocumentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public GlobalErrorResponse handleDocumentNotFoundException(DocumentNotFoundException ex){
        return new GlobalErrorResponse(ex.getDefaultGlobalCode(), ex.getDefaultUserMessage());
    }
}
