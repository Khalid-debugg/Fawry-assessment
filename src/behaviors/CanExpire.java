package behaviors;

import java.time.LocalDate;

public class CanExpire implements Expirable {
    private final LocalDate expiryDate;

    public CanExpire(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }
}