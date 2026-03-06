package it.unibo.pps.e1;

public class BronzeBankAccount implements BankAccount{

    private CoreBankAccount base;
    private static final int FEE_LIMIT = 100;
    private static final int BRONZE_FEE = 1;

    public BronzeBankAccount(CoreBankAccount account) {
        base = account;
    }

    @Override
    public int getBalance() {
        return base.getBalance();
    }

    @Override
    public void deposit(int amount) {
        base.deposit(amount);
    }

    @Override
    public void withdraw(int amount) {
        if (this.getBalance() < amount){
            throw new IllegalStateException();
        }
        base.withdraw(amount + (amount < FEE_LIMIT ? 0 : BRONZE_FEE));
    }
}
