package dev.hugo;

public class SavingsAccount extends Account {
    private boolean active;

    public SavingsAccount(float balance, float annualRate) {
        super(balance, annualRate);
        active = balance >= 10000;
    }

    @Override
    public void deposit(float amount) {
        if (active)
            super.deposit(amount);
        if (balance >= 10000) {
            active = true;
        }
    }


    @Override
    public void withdraw(float amount) {
        if (active)
            super.withdraw(amount);
        else
            throw new IllegalArgumentException("Error: Cannot withdraw from an inactive account");
    }

   

    @Override
    public void monthlyStatement() {
        if (numWihtdrawals > 4) {
            monthlyFee += (numWihtdrawals - 4) * 1000;
        }
        // Calculate monthly interest before applying monthly fee
        calculateMonthlyInterest();
        balance -= monthlyFee;
        if (balance < 10000)
            active = false;
    }

    public boolean isActive() {
        return active;
    }

    public void print() {
        System.out.println("Balance = $ " + balance);
        System.out.println("Monthly Fee = $ " + monthlyFee);
        System.out.println("Number of Transactions = " + (numDeposits + numWihtdrawals));
        System.out.println();
    }
}
