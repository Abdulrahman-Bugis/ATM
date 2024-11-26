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
public class CurrentAccount extends Account {
    public CurrentAccount(String accountNumber, double initialDeposit) {
        super(accountNumber, initialDeposit);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposited: " + amount);
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > balance) {
            transactionHistory.add("Failed withdrawal: Insufficient funds");
            return false;
        }
        balance -= amount;
        transactionHistory.add("Withdrawn: " + amount);
        return true;
    }
}

