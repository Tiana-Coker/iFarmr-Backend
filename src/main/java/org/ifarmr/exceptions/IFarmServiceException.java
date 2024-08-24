package org.ifarmr.exceptions;

public class IFarmServiceException extends RuntimeException{
    public IFarmServiceException(String message, Throwable cause){
        super(message, cause);
    }
}
