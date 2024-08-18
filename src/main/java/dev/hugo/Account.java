package dev.hugo;

public class Account {
    protected float balance;
    protected int numDeposits;
    protected int numWitdrawals;
    protected float annualInterestRate;
    protected float monthlyFee;

    public Account(float balance, float annualInterestRate) {
        this.balance = balance;
        this.numDeposits = 0;
        this.numWitdrawals = 0;
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

    public int getNumWitdrawals() {
        return numWitdrawals;
    }

    public void setNumWitdrawals(int numWitdrawals) {
        this.numWitdrawals = numWitdrawals;
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

    
}
