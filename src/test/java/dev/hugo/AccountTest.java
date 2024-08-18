package dev.hugo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {

    private Account account;

    @BeforeEach
    public void setUp() {
        account = new Account(1000.0f, 0.05f);
    }

    @Test
    public void testInitialValues() {
        assertThat(account.getBalance(), is(1000.0f));
        assertThat(account.getAnnualInterestRate(), is(0.05f));
        assertThat(account.getNumDeposits(), is(0));
        assertThat(account.getNumWitdrawals(), is(0));
        assertThat(account.getMonthlyFee(), is(0.0f));
    }

    @Test
    public void testSetters() {
        account.setBalance(1500.0f);
        account.setNumDeposits(5);
        account.setNumWitdrawals(3);
        account.setAnnualInterestRate(0.07f);
        account.setMonthlyFee(10.0f);

        assertThat(account.getBalance(), is(1500.0f));
        assertThat(account.getAnnualInterestRate(), is(0.07f));
        assertThat(account.getNumDeposits(), is(5));
        assertThat(account.getNumWitdrawals(), is(3));
        assertThat(account.getMonthlyFee(), is(10.0f));
    }

}
