package dev.hugo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SavingsAccountTest {

    private SavingsAccount savingsAccount;

    @BeforeEach
    public void setUp() {
        savingsAccount = new SavingsAccount(12000.0f, 6.0f);  // Initialize with active status
    }

    @Test
    public void testInitialValues() {
        assertThat(savingsAccount.getBalance(), is(12000.0f));
        assertThat(savingsAccount.getAnnualInterestRate(), is(6.0f));
        assertThat(savingsAccount.getNumDeposits(), is(0));
        assertThat(savingsAccount.getNumWithdrawals(), is(0));
        assertThat(savingsAccount.getMonthlyFee(), is(0.0f));
        assertThat(savingsAccount.isActive(), is(true));
    }

    @Test
    public void testDepositInActiveAccount() {
        savingsAccount.deposit(5000.0f);
        assertThat(savingsAccount.getBalance(), is(17000.0f));
        assertThat(savingsAccount.getNumDeposits(), is(1));
        assertThat(savingsAccount.isActive(), is(true));
    }

 

    @Test
    public void testWithdrawFromActiveAccount() {
        savingsAccount.withdraw(1000.0f);
        assertThat(savingsAccount.getBalance(), is(11000.0f));
        assertThat(savingsAccount.getNumWithdrawals(), is(1));
        assertThat(savingsAccount.isActive(), is(true));
    }

    @Test
    public void testWithdrawFromInactiveAccount() {
        savingsAccount = new SavingsAccount(9000.0f, 6.0f); // Set to inactive
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            savingsAccount.withdraw(500.0f);
        });
        assertThat(exception.getMessage(), is("Error: Cannot withdraw from an inactive account"));
    }

    @Test
    public void testMonthlyStatementWithNoExtraWithdrawals() {
        savingsAccount.monthlyStatement();
        assertThat(savingsAccount.getBalance(), is(12060.0F)); // No fee should be added
        assertThat(savingsAccount.getMonthlyFee(), is(0.0f));
        assertThat(savingsAccount.isActive(), is(true));
    }

    @Test
    public void testMonthlyStatementWithExtraWithdrawals() {
        
        savingsAccount.withdraw(1000.0f); 
        savingsAccount.withdraw(500.0f);  
        savingsAccount.withdraw(100.0f);  
        savingsAccount.withdraw(200.0f);  
        savingsAccount.withdraw(200.0f);  
        savingsAccount.withdraw(100.0f);  

        savingsAccount.monthlyStatement();

        // Calcula la tarifa mensual: 2 retiros extra * 1000
        float expectedFee = 2000.0f;
       
        float expectedBalance = 9900.0f * 1.005f - expectedFee; // 9900 + 0.5% de interés mensual - tarifa
        assertThat(savingsAccount.getBalance(), is(expectedBalance));
        assertThat(savingsAccount.getMonthlyFee(), is(expectedFee));
        assertThat(savingsAccount.isActive(), is(false)); // La cuenta se vuelve inactiva si el balance es < 10000
    }
   

    @Test
    public void testPrint() {       
    }
}
