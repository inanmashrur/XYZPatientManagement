package org.xyz.patientmanagement.exception;

/**
 * @author inanmashrur
 * @since 04/01/2023
 */
public class UnauthorizedException extends RuntimeException {

    private static final String DEFAULT_MSG = "Access Denied!";

    public UnauthorizedException() {
        super(DEFAULT_MSG);
    }
}
