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

public class ATM {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATMController controller = ATMController.getInstance(); // Singleton
        AccountNotifier notifier = new AccountNotifier(); // Observer
        Observer user1 = new Notification("User1");
        notifier.addObserver(user1);

        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Balance Inquiry");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter account number: ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("Enter initial deposit: ");
                    double initialDeposit = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    controller.createAccount(accountNumber, initialDeposit);
                    notifier.notifyObservers("New account created: " + accountNumber);
                    System.out.println("Account created successfully!");
                    break;

                case 2:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextLine();
                    Account account = controller.getAccount(accountNumber);
                    if (account != null) {
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        account.deposit(depositAmount);
                        notifier.notifyObservers("Deposit made: " + depositAmount + " to account " + accountNumber);
                        System.out.println("Deposit successful!");
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 3:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextLine();
                    account = controller.getAccount(accountNumber);
                    if (account != null) {
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawalAmount = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        if (account.withdraw(withdrawalAmount)) {
                            notifier.notifyObservers("Withdrawal made: " + withdrawalAmount + " from account " + accountNumber);
                            System.out.println("Withdrawal successful!");
                        } else {
                            System.out.println("Insufficient funds!");
                        }
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 4:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextLine();
                    account = controller.getAccount(accountNumber);
                    if (account != null) {
                        System.out.println("Current balance: " + account.getBalance());
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 5:
                    System.out.print("Enter account number: ");
                    accountNumber = scanner.nextLine();
                    account = controller.getAccount(accountNumber);
                    if (account != null) {
                        System.out.println("Transaction History:");
                        account.getTransactionHistory().forEach(System.out::println);
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 6:
                    System.out.println("Thank you for using the ATM!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
