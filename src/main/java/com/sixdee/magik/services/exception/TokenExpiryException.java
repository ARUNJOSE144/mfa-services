package com.sixdee.magik.services.exception;

/**
 * @author Basil Jose
 * @version 1.0.0.0
 * @since Jul 5, 2018 : 11:37:36 AM
 */

public class TokenExpiryException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TokenExpiryException(String message) {
        super(message);
    }

}
