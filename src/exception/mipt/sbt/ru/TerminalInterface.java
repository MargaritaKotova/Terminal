package exception.mipt.sbt.ru;

/**
 * Created by 11007139 on 17.12.2017.
 */
public interface TerminalInterface {
    int checkBalance() throws NotValidatedPinException, ConnectionProblemException;

    void addMoney(int sum) throws NotValidatedPinException, WrongSumException, ConnectionProblemException;


    void getMoney(int sum) throws NotValidatedPinException, WrongSumException, MoneyIsNotEnoughException, ConnectionProblemException;

    void validatePin(String pin) throws WrongPinException, AccountLockedException;

    boolean isPinValidated();
}
