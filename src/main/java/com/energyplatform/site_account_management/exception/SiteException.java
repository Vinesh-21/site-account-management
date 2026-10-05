package com.energyplatform.site_account_management.exception;

public class SiteException extends RuntimeException {

    public SiteException(String message) {
        super(message);
    }

    public SiteException(String message, Throwable cause) {
        super(message, cause);
    }
}
