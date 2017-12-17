package exception.mipt.sbt.ru;


public class TerminalImpl implements TerminalInterface {
    private final TerminalServer server;
    private final PinValidator pinValidator;
    private boolean isPinValidated;

    public TerminalImpl(TerminalServer server, PinValidator pinValidator) {
        this.server = server;
        this.pinValidator = pinValidator;
        this.isPinValidated = false;
    }

    @Override
    public int checkBalance() throws NotValidatedPinException, ConnectionProblemException {
        if (!isPinValidated) {
            throw new NotValidatedPinException();
        }

        return server.checkBalance();
    }

    @Override
    public void addMoney(int sum) throws NotValidatedPinException, WrongSumException, ConnectionProblemException {
        if (!isPinValidated) {
            throw new NotValidatedPinException();
        }

        if (sum % 100 != 0 || sum < 0) {
            throw new WrongSumException();
        }

        server.addMoney(sum);
    }

    @Override
    public void getMoney(int sum) throws NotValidatedPinException, WrongSumException, MoneyIsNotEnoughException, ConnectionProblemException {
        if (!isPinValidated) {
            throw new NotValidatedPinException();
        }

        if (sum % 100 != 0 || sum < 0) {
            throw new WrongSumException();
        }

        server.getMoney(sum);
    }

    @Override
    public void validatePin(String pin) throws WrongPinException, AccountLockedException {
        pinValidator.validatePin(pin);
        isPinValidated = true;

    }

    @Override
    public boolean isPinValidated() {
        return isPinValidated;
    }
}
