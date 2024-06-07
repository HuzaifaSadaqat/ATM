/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Dell
 */
public class ATM {

    private boolean userAuthenticated; // whether user is authenticated
    private Account account; // current user's account number
    private Screen screen; // ATM's screen
    private Keypad keypad; // ATM's keypad
    private CashDispenser cashDispenser; // ATM's cash dispenser
    private DepositSlot depositSlot; // ATM's deposit slot
    private BankDatabase bankDatabase; // account information database
    private Transaction currentTransaction;
    // constants corresponding to main menu options
    private static final int BALANCE_INQUIRY = 1;
    private static final int WITHDRAWAL = 2;
    private static final int DEPOSIT = 3;
    private static final int RECORD = 4;

    private static final int EXIT = 5;

    // no-argument ATM constructor initializes instance variables
    public ATM() {
        userAuthenticated = false; // user is not authenticated to start
        account = new Account();
        screen = new Screen(); // create screen
        keypad = new Keypad(); // create keypad
        cashDispenser = new CashDispenser(); // create cash dispenser
        depositSlot = new DepositSlot(); // create deposit slot
        bankDatabase = new BankDatabase(); // create acct info database
        currentTransaction = null;
    }

    // start ATM
    public void run() {
        // welcome and authenticate user; perform transactions

        while (true) {
            // loop while user is not yet authenticated

            while (!userAuthenticated) {
                screen.displayMessageLine("\nWelcome!");
                authenticateUser(); // authenticate user
            }

            performTransactions(); // user is now authenticated
            userAuthenticated = false; // reset before next ATM session
            account.setAccountNumber(0); // reset before next ATM session
            screen.displayMessageLine("\nThank you! Goodbye!");
        }
    }

    // attempts to authenticate user against database
    private void authenticateUser() {
        screen.displayMessage("\nPlease enter your account number: ");
        int accountNumber = keypad.getInput(); // input account number
        screen.displayMessage("\nEnter your PIN: "); // prompt for PIN
        int pin = keypad.getInput(); // input PIN
        // set userAuthenticated to boolean value returned by database
        userAuthenticated = bankDatabase.authenticateUser(accountNumber, pin);

        // check whether authentication succeeded
        if (userAuthenticated) {
            account.setAccountNumber(accountNumber);  // save user's account #
        } else {
            screen.displayMessageLine(
                    "Invalid account number or PIN. Please try again.");
        }
    }

    // display the main menu and perform transactions
    private void performTransactions() {
// local variable to store transaction currently being processed

        boolean userExited = false; // user has not chosen to exit

// loop while user has not chosen option to exit system
        while (!userExited) {
// show main menu and get user selection
            int mainMenuSelection = displayMainMenu();

// decide how to proceed based on user's menu selection
            switch (mainMenuSelection) {
// user chose to perform one of three transaction types
                case BALANCE_INQUIRY:
                case WITHDRAWAL:
                case DEPOSIT:
                    // initialize as new object of chosen type
                    currentTransaction = createTransaction(mainMenuSelection);
                    currentTransaction.execute(); // execute transaction
                    break;
                case RECORD:
                    account.displayRecords();
                    break;
                case EXIT: // user chose to terminate session
                    screen.displayMessageLine("\nExiting the system...");
                    userExited = true; // this ATM session should end
                    break;
                default: // user did not enter an integer from 1-4
                    screen.displayMessageLine(
                            "\nYou did not enter a valid selection. Try again.");
                    break;
            }
        }
    }

    // display the main menu and return an input selection
    private int displayMainMenu() {
        screen.displayMessageLine("\nMain menu:");
        screen.displayMessageLine("1 - View my balance");
        screen.displayMessageLine("2 - Withdraw cash");
        screen.displayMessageLine("3 - Deposit funds");
        screen.displayMessageLine("4 - View Bank Statement");
        screen.displayMessageLine("5 - Exit\n");
        screen.displayMessage("Enter a choice: ");
        return keypad.getInput(); // return user's selection
    }

    // return object of specified Transaction subclass
    private Transaction createTransaction(int type) {
        Transaction temp = null; // temporary Transaction variable
        Withdrawal withdrawal = null;
        Deposit deposit = null;
        BalanceInquiry balanceinquiry = null;

        // determine which type of Transaction to create
        switch (type) {
            case BALANCE_INQUIRY: // create new BalanceInquiry transaction
                temp = new BalanceInquiry(account.getAccountNumber(), screen, bankDatabase);
                break;
            case WITHDRAWAL: // create new Withdrawal transaction
                temp = new Withdrawal(account.getAccountNumber(), screen, bankDatabase, keypad, cashDispenser);

                withdrawal = new Withdrawal(account.getAccountNumber(), screen, bankDatabase, keypad, cashDispenser);
                withdrawal.execute(); // execute the transaction
                int amount = withdrawal.getAmount();

                Record record = new Record(account.getAccountNumber(), "Withdraw", new Date(), amount);
                account.addRecord(record);

                break;
            case DEPOSIT: // create new Deposit transaction
                temp = new Deposit(account.getAccountNumber(), screen, bankDatabase, keypad, depositSlot);

                deposit = new Deposit(account.getAccountNumber(), screen, bankDatabase, keypad, depositSlot);
                deposit.execute(); // execute the transaction
                int depositAmount = deposit.getAmount();

                Record depositRecord = new Record(account.getAccountNumber(), "Deposit", new Date(), depositAmount);
                account.addRecord(depositRecord);

                break;
        }

        return temp; // return the newly created object
    }

}
