package exception.mipt.sbt.ru;

/**
 * Created by 11007139 on 17.12.2017.
 */
public interface TerminalServer {
    int checkBalance() throws ConnectionProblemException;
    void addMoney(int sum) throws ConnectionProblemException;
    void getMoney(int sum) throws ConnectionProblemException, MoneyIsNotEnoughException;
}
