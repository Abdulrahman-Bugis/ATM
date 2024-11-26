/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm_2236125;

/**
 *
 * @author afbugis
 */
import java.util.*;

public class ATMController {
    private static ATMController instance; // Singleton instance
    private Map<String, Account> accounts = new HashMap<>();

    private ATMController() {} // Private constructor to prevent instantiation

    public static ATMController getInstance() {
        if (instance == null) {
            instance = new ATMController();
        }
        return instance;
    }

    public Account createAccount(String accountNumber, double initialDeposit) {
        Account account = new CurrentAccount(accountNumber, initialDeposit);
        accounts.put(accountNumber, account);
        return account;
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }
}

