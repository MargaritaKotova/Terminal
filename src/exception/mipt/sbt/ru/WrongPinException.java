package exception.mipt.sbt.ru;

public class WrongPinException extends Throwable {
    private final int remainingAttempts;

    public WrongPinException(int remainingAttempts) {
        this.remainingAttempts = remainingAttempts;
    }

    public int getRemainingAttempts() {
        return remainingAttempts;
    }
}