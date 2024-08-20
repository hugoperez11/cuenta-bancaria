package dev.hugo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CurrentAccTest {

    private CurrentAcc currentAcc;
      

    @BeforeEach
    public void setUp() {
        currentAcc = new CurrentAcc(5000.0f, 3.5f);

       
    }

    @Test
    public void testInitialBalanceAndInterestRate() {
        assertThat(currentAcc.getBalance(), is(5000.0f));
        assertThat(currentAcc.getAnnualInterestRate(), is(3.5f));
    }

    @Test
    public void testInitialOverdraft() {
        assertThat(currentAcc.overdraft, is(0.0f));
    }



    @Test
    public void testWithdrawWithinBalance() {
        currentAcc.withdraw(2000.0f);
        
        assertThat(currentAcc.getBalance(), is(3000.0f));
        
        assertThat(currentAcc.overdraft, is(0.0f));
    }
    @Test
    public void testWithdrawWithOverdraft() {
        currentAcc.withdraw(6000.0f);
        
        assertThat(currentAcc.getBalance(), is(0.0f));
        
        assertThat(currentAcc.overdraft, is(1000.0f));
   
    }
        @Test
    public void testWithdrawNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> currentAcc.withdraw(-1000.0f));
        
        assertThat(currentAcc.getBalance(), is(5000.0f));
        assertThat(currentAcc.overdraft, is(0.0f));
    }

    @Test
    public void testDepositWithOverdraftFullCover() {
        currentAcc.withdraw(6000.0f); // Create an overdraft of 1000.0f
        currentAcc.deposit(1000.0f); // Deposit enough to cover the overdraft
    
        assertThat(currentAcc.getBalance(), is(0.0f)); // Balance should be zero
        assertThat(currentAcc.overdraft, is(0.0f)); // Overdraft should be zero
    }
    

    @Test
    public void testDepositWithoutOverdraft() {
        currentAcc.deposit(2000.0f);
        
        assertThat(currentAcc.getBalance(), is(7000.0f));
        
        assertThat(currentAcc.overdraft, is(0.0f));
    }

    @Test
    public void testDepositWithOverdraftPartialCover() {
        currentAcc.withdraw(6000.0f);
        
        currentAcc.deposit(500.0f);
        
        assertThat(currentAcc.getBalance(), is(0.0f));
        
        assertThat(currentAcc.overdraft, is(500.0f));
    }

    @Test
    public void testMonthlyStatement() {
     
        currentAcc.monthlyStatement();

        // Calculate the expected balance after monthly interest is applied
        // Monthly interest calculation: balance * (annualRate / 12 / 100)
        // Example calculation: 5000 * (3.5 / 12 / 100) = 14.58333
        float expectedBalance = 5000.0f + (5000.0f * (3.5f / 12 / 100));

        // Since the super method is used, we expect the balance to reflect this calculation
        assertThat(currentAcc.getBalance(), is(expectedBalance));
    }
 

}


