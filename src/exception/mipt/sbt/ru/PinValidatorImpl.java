package exception.mipt.sbt.ru;

import java.util.Date;

public class PinValidatorImpl implements PinValidator {
    private static final int MILLISECONDS_IN_SECOND = 1000;

    private static final int PENALTY_SECONDS = 5;
    private static final int MAX_ATTEMPTS = 3;

    private final String pin;
    private int wrongAttempts;
    private Date lastWrongAttempt;
    private boolean isAccessLocked;

    public PinValidatorImpl(String pin) {
        this.pin = pin;
        this.wrongAttempts = 0;
        this.isAccessLocked = false;
    }

    @Override
    public void validatePin(String pin) throws WrongPinException, AccountLockedException {
        if (isAccessLocked &&
                ((new Date().getTime() - lastWrongAttempt.getTime()) / MILLISECONDS_IN_SECOND) < PENALTY_SECONDS) {
            throw new AccountLockedException(PENALTY_SECONDS -
                    (int)((new Date().getTime() - lastWrongAttempt.getTime()) / MILLISECONDS_IN_SECOND));
        }

        if (this.pin.equals(pin)) {
            rightEnteredPin();
        } else {
            wrongEnteredPin();
        }
    }

    private void wrongEnteredPin() throws AccountLockedException, WrongPinException {
        wrongAttempts++;
        lastWrongAttempt = new Date();

        if (wrongAttempts >= MAX_ATTEMPTS) {
            isAccessLocked = true;
            throw new AccountLockedException(PENALTY_SECONDS -
                    (int)((new Date().getTime() - lastWrongAttempt.getTime()) / MILLISECONDS_IN_SECOND));
        }

        throw new WrongPinException(MAX_ATTEMPTS - wrongAttempts);
    }

    private void rightEnteredPin() {
        wrongAttempts = 0;
        isAccessLocked = false;
    }
}