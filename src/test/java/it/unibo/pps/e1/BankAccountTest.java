package it.unibo.pps.e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

abstract class BankAccountTest {

    protected BankAccount account;
    protected int depositAmount;

    protected abstract BankAccount createAccount();

    @BeforeEach
    void init() {
        this.account = createAccount();
        this.depositAmount = 1000;
    }

    @Test
    void testInitiallyEmpty() {
        assertEquals(0, account.getBalance());
    }

    @Test
    void testCanDeposit() {
        account.deposit(depositAmount);
        assertEquals(depositAmount, account.getBalance());
    }

    @Test
    void testCannotWithdrawMoreThanAvailable() {
        account.deposit(depositAmount);
        assertThrows(IllegalStateException.class, () -> account.withdraw(1200));
    }
}

class SilverBankAccountTest extends BankAccountTest {
    @Override
    protected BankAccount createAccount() {
        return new SilverBankAccount(new CoreBankAccount());
    }

    @Test
    void testCanWithdrawWithFee() {
        account.deposit(depositAmount);
        account.withdraw(200);
        assertEquals(799, account.getBalance());
    }
}

class GoldBankAccountTest extends BankAccountTest {
    @Override
    protected BankAccount createAccount() {
        return new GoldBankAccount(new CoreBankAccount());
    }

    @Override
    @Test
    void testCannotWithdrawMoreThanAvailable() {
        account.deposit(depositAmount);
        account.withdraw(1400);
        assertEquals(-400, account.getBalance());
        assertThrows(IllegalStateException.class, () -> account.withdraw(200));
    }

    @Test
    void testCanWithdrawWithoutFee() {
        account.deposit(depositAmount);
        account.withdraw(200);
        assertEquals(800, account.getBalance());
    }
}

class BronzeBankAccountTest extends BankAccountTest {
    @Override
    protected BankAccount createAccount() {
        return new BronzeBankAccount(new CoreBankAccount());
    }

    @ParameterizedTest
    @CsvSource({
            "150,  50,  100",
            "200,  101,  98",
    })
    void testCanWithdrawWithFeeLimit(int deposit, int withdraw, int expected) {
        account.deposit(deposit);
        account.withdraw(withdraw);
        assertEquals(expected, account.getBalance());
    }

}
