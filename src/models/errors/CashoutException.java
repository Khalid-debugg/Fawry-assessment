package models.errors;

public class CashoutException extends RuntimeException {
    public CashoutException(String message) {
        super(message);
    }
}
