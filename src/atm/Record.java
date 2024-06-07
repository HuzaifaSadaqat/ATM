/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Dell
 */
public class Record {

    private int acoountNumber;
    String transactionType;
    Date date;
    int amount;

    public Record(int acoountNumber, String transactionType, Date date, int amount) {
        this.acoountNumber = acoountNumber;
        this.transactionType = transactionType;
        this.date = date;
        this.amount = amount;
    }


    public int getAcoountNumber() {
        return acoountNumber;
    }

    public void setAcoountNumber(int acoountNumber) {
        this.acoountNumber = acoountNumber;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "Account Number: " + acoountNumber
                + ", Transaction Type: " + transactionType
                + ", Date: " + dateFormat.format(date)
                + ", Amount: " + amount;
    }
}
