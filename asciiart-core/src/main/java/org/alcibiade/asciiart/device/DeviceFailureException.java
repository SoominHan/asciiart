package org.alcibiade.asciiart.device;

public class DeviceFailureException extends Exception {

    private static final long serialVersionUID = 1L;

    public DeviceFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeviceFailureException(String message) {
        super(message);
    }
}
