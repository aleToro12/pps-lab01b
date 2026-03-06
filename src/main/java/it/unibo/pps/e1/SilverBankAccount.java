package it.unibo.pps.e1;

public class SilverBankAccount implements BankAccount{

    private CoreBankAccount base;
    private static final int SILVER_FEE = 1;

    public SilverBankAccount(CoreBankAccount account) {
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
        base.withdraw(amount + SILVER_FEE);
    }
}
