package dev.hugo;

import java.util.Locale;

public class Account {
    protected float balance;
    protected int numDeposits;
    protected int numWihtdrawals;
    protected float annualInterestRate;
    protected float monthlyFee;

    public Account(float balance, float annualInterestRate) {
        this.balance = balance;
        this.numDeposits = 0;
        this.numWihtdrawals = 0;
        this.annualInterestRate = annualInterestRate;
        this.monthlyFee = 0.0f;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getNumDeposits() {
        return numDeposits;
    }

    public void setNumDeposits(int numDeposits) {
        this.numDeposits = numDeposits;
    }

    public int getNumWithdrawals() {
        return numWihtdrawals;
    }

    public void setNumWithdrawals(int numWitdrawals) {
        this.numWihtdrawals = numWitdrawals;
    }

    public float getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(float annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public float getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(float monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public void deposit(float amount) {
        if (amount > 0) {
            balance += amount;
            numDeposits++;
        }
    }

    public void withdraw(float amount) {
        if(amount > 0 && amount <= balance){
            balance-= amount;
            numWihtdrawals++;           
     }else{
        throw new IllegalArgumentException("Error: Can not withdraw amount bigger than balance");
     }
    }

    public void calculateMonthlyInterest() {
        if(balance > 0) {
            float monthlyInterest = balance*(annualInterestRate/12/100);
            balance += monthlyInterest;
        }
    }

    public void monthlyStatement() {       

        calculateMonthlyInterest();
        balance -= monthlyFee;
    }


  @Override
public String toString() {
    return String.format(Locale.forLanguageTag("es-ES"), 
        "Account { balance=%.2f, numDeposits=%d, numWithdrawals=%d, annualInterestRate=%.2f%%, monthlyFee=%.2f }",
        balance, numDeposits, numWihtdrawals, annualInterestRate, monthlyFee);
}

}
