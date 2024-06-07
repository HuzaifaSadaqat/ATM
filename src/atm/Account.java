package atm;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private int accountNumber;
    private int pin;
    private double availableBalance;
    private double totalBalance;
    private List<Record> records; // Changed 'Records' to 'records'

    public Account() {
        this.accountNumber = 0;
        this.pin = 0;
        this.availableBalance = 0;
        this.totalBalance = 0;
        this.records = new ArrayList<>(); // Changed 'Records' to 'records'
    }

    public Account(int theAccountNumber, int thePIN,
            double theAvailableBalance, double theTotalBalance) {
        accountNumber = theAccountNumber;
        pin = thePIN;
        availableBalance = theAvailableBalance;
        totalBalance = theTotalBalance;
        records = new ArrayList<>(); // Changed 'Records' to 'records'
    }

    public boolean validatePIN(int userPIN) {
        return userPIN == pin;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public void credit(double amount) {
        totalBalance += amount;
    }

    public void debit(double amount) {
        availableBalance -= amount;
        totalBalance -= amount;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<Record> getRecords() {
        return records; // Changed 'Records' to 'records'
    }

    public void displayRecords() {
        for (Record record : records) { // Changed 'Records' to 'records'
            System.out.println(record);
        }
    }

    public void addRecord(Record record) {
        records.add(record); // Changed 'Records' to 'records'
    }

    // Add other getters and setters as needed
}
