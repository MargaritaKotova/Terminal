package exception.mipt.sbt.ru;

public class AccountLockedException extends Throwable {
        private final int remainingSeconds;

        public AccountLockedException(int remainingSeconds) {
            this.remainingSeconds = remainingSeconds;
        }

        public int getRemainingSeconds() {
            return remainingSeconds;
        }
    }

