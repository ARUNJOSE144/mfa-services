package com.inno.mfa.services.exception;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */
public class TokenExpiryException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TokenExpiryException(String message) {
        super(message);
    }

}
