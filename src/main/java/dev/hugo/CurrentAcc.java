package dev.hugo;

public class CurrentAcc extends Account {
    float overdraft;

 
    public CurrentAcc(float balance, float interestRate) {
        super(balance, interestRate);
        overdraft = 0;
    }

    public void withdraw(float amount) {
        float result = balance - amount;
        if (result < 0) {
            overdraft = overdraft - result;
            balance = 0;
        } else {
            super.withdraw(amount);
        }
    }

    public void deposit(float amount) {
        if (overdraft > 0) {
            if (amount >= overdraft) {
                amount -= overdraft;  
                overdraft = 0;
                balance += amount;  
            } else {
                overdraft -= amount;  
            }
        } else {
            super.deposit(amount);  
        }
    }
    

    public void monthlyStatement() {
        super.monthlyStatement();
    }

    public void printStatement() {
        System.out.println("Balance = $ " + balance);
        System.out.println("Monthly Fee = $ " + monthlyFee);
        System.out.println("Number of Transactions = " + (numDeposits + numWihtdrawals));
        System.out.println("Overdraft = $" + overdraft);
        System.out.println();
    }
}
