package atm;

public abstract class Transaction {

    private int accountNumber; // indicates account involved
    private Screen screen; // ATM's screen
    private BankDatabase bankDatabase; // account info database

    // Transaction constructor invoked by subclasses using super()
    public Transaction(int userAccountNumber, Screen atmScreen,
            BankDatabase atmBankDatabase) {

        accountNumber = userAccountNumber;
        screen = atmScreen;
        bankDatabase = atmBankDatabase;
    }

    // return account number
    public int getAccountNumber() {
        return accountNumber;
    }

    // return reference to screen
    public Screen getScreen() {
        return screen;
    }

    // return reference to bank database

    /**
     *
     * @return
     */
    public BankDatabase getBankDatabase() {
        return bankDatabase;
    }

    // perform the transaction (overridden by each subclass)
    abstract public void execute();
}
