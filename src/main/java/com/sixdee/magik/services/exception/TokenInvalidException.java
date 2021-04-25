package com.sixdee.magik.services.exception;

/**
 * @author Basil Jose
 * @version 1.0.0.0
 * @since Jul 5, 2018 : 11:37:47 AM
 */

public class TokenInvalidException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TokenInvalidException(String message) {
        super(message);
    }

}
