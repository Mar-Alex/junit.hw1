package school.lemon.changerequest.java.junit.hw1.api;

/**
 * Shows that some contact wasn't found
 */
public class ContactNotFoundException extends RuntimeException {
    public static final String NO_CONTACT_FOR_CALL_NUMBER = "001";
    public static final String NO_CONTACT_WITH_ID = "002";
    private static final String ERROR_MESSAGE_FORMAT = "ErrorCode: %s. %s";

    public ContactNotFoundException(String message, String errorCode) {
        super(String.format(ERROR_MESSAGE_FORMAT, errorCode, message));
    }
}
