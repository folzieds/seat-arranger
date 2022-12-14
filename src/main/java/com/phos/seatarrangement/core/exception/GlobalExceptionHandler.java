package com.phos.seatarrangement.core.exception;

import com.phos.seatarrangement.core.useradministration.exception.RoleNotFoundException;
import com.phos.seatarrangement.document.exception.DocumentNotFoundException;
import com.phos.seatarrangement.event.exception.EventNotFoundException;
import com.phos.seatarrangement.guest.exception.GuestNotFoundException;
import com.phos.seatarrangement.core.useradministration.exception.UnAuthenticatedUserException;
import com.phos.seatarrangement.core.useradministration.exception.UserNotFoundException;
import com.phos.seatarrangement.core.useradministration.exception.UsernameAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public GlobalErrorResponse handleGlobalException(Exception ex){
        String localizedMessage = ex.getLocalizedMessage().split(":")[0];
        String message = ex.getMessage().split(":")[0];
        return new GlobalErrorResponse(localizedMessage, message);
    }

    @ExceptionHandler(PlatformDataIntegrityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
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

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public GlobalErrorResponse handleUserNotFoundException(UserNotFoundException ex){
        return new GlobalErrorResponse(ex.getDefaultGlobalCode(), ex.getDefaultUserMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public GlobalErrorResponse handleUsernameNotFoundException(UsernameNotFoundException ex){
        return new GlobalErrorResponse(ex.getLocalizedMessage(), ex.getMessage());
    }

    @ExceptionHandler(UsernameAlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public GlobalErrorResponse handleUsernameAlreadyExistsException(UsernameAlreadyExistException ex){
        return new GlobalErrorResponse(ex.getDefaultGlobalCode(), ex.getDefaultUserMessage());
    }

    @ExceptionHandler(UnAuthenticatedUserException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public GlobalErrorResponse handleUnauthenticatedUserException(UnAuthenticatedUserException ex){
        return new GlobalErrorResponse(ex.getDefaultGlobalCode(), ex.getDefaultUserMessage());
    }

    @ExceptionHandler(RoleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public GlobalErrorResponse handleRoleNotFoundException(RoleNotFoundException ex){
        return new GlobalErrorResponse(ex.getDefaultGlobalCode(), ex.getDefaultUserMessage());
    }
}
