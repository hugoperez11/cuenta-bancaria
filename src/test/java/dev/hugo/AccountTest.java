package dev.hugo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {

    private Account account;

    @BeforeEach
    public void setUp() {
        account = new Account(1000.0f, 6.0f);
    }

    @Test
    public void testInitialValues() {
        assertThat(account.getBalance(), is(1000.0f));
        assertThat(account.getAnnualInterestRate(), is(6.0f));
        assertThat(account.getNumDeposits(), is(0));
        assertThat(account.getNumWithdrawals(), is(0));
        assertThat(account.getMonthlyFee(), is(0.0f));
    }

    @Test
    public void testSetters() {
        account.setBalance(1500.0f);
        account.setNumDeposits(5);
        account.setNumWithdrawals(3);
        account.setAnnualInterestRate(0.07f);
        account.setMonthlyFee(10.0f);

        assertThat(account.getBalance(), is(1500.0f));
        assertThat(account.getAnnualInterestRate(), is(0.07f));
        assertThat(account.getNumDeposits(), is(5));
        assertThat(account.getNumWithdrawals(), is(3));
        assertThat(account.getMonthlyFee(), is(10.0f));
    }

    // test for deposit
    @Test
    public void testDepositIncrease() {
        account.deposit(500.0f);

        assertThat(account.getBalance(), is(1500.0f));
        assertThat(account.getNumDeposits(), is(1));
    }

    @Test
    public void testDepositZeroAmount() {

        account.deposit(0.0f);

        assertThat(account.getBalance(), is(1000.0f));
        assertThat(account.getNumDeposits(), is(0));
    }

    @Test
    public void testDepositNegativeAmount() {
        account.deposit(-100.0f);

        assertThat(account.getBalance(), is(1000.0f));
        assertThat(account.getNumDeposits(), is(0));
    }

    // test withdraw

    @Test
    public void testWithdrawValidAmount() {
        account.withdraw(500.0f);

        assertThat(account.getBalance(), is(500.0f));
        assertThat(account.getNumWithdrawals(), is(1));
    }

    @Test
    public void testWithdrawAmountExceedsBalance() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(1500.0f);
        });

        assertThat(exception.getMessage(), containsString("Error: Can not withdraw amount bigger than balance"));
    }

    // test interest

    @Test
    public void testCalculateMonthlyInterestWithPositiveBalance() {
        Account account = new Account(1200.0f, 6.0f);

        account.calculateMonthlyInterest();

        assertThat(account.getBalance(), is(1206.0f));
    }

    @Test
    public void testCalculateMonthlyInterestWithZeroBalance() {

        Account account = new Account(0.0f, 6.0f);

        account.calculateMonthlyInterest();

        assertThat(account.getBalance(), is(0.0f));
    }

    // test statement
    @Test
    public void testMonthlyStatementWithPositiveBalance() {

        Account account = new Account(1200.0f, 6.0f);
        account.monthlyFee = 10.0f;
        account.monthlyStatement();

        assertThat(account.getBalance(), is(1196.0f));
    }

    @Test
    public void testMonthlyStatementWithZeroBalance() {
        Account account = new Account(0.0f, 6.0f);
        account.monthlyFee = 10.0f;

        account.monthlyStatement();

        assertThat(account.getBalance(), is(-10.0f));
    }

    @Test
    public void testMonthlyStatementWithNegativeBalance() {
        Account account = new Account(-500.0f, 6.0f);
        account.monthlyFee = 10.0f;

        account.monthlyStatement();

        assertThat(account.getBalance(), is(-510.0f));
    }

    @Test
    public void testToStringMethodWithSpanishLocale() {
        // Configuración de la cuenta con valores específicos
        Account account = new Account(1200.57f, 6.0f);
        account.setNumDeposits(3);
        account.setNumWithdrawals(1);
        account.setMonthlyFee(10.0f);

        // Cadena esperada ajustada al Locale español
        String expectedString = "Account { balance=1200,57, numDeposits=3, numWithdrawals=1, annualInterestRate=6,00%, monthlyFee=10,00 }";

        // Ejecución y verificación
        assertThat(account.toString(), is(expectedString));
    }
}
