package com.inno.mfa.services.exception;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */

public class TokenInvalidException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TokenInvalidException(String message) {
        super(message);
    }

}
