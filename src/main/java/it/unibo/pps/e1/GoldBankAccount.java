package it.unibo.pps.e1;

public class GoldBankAccount implements BankAccount{

    private CoreBankAccount base;
    private static final int OVERDRAFT = 500;

    public GoldBankAccount(CoreBankAccount account) {
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
        if (this.getBalance() + OVERDRAFT < amount){
            throw new IllegalStateException();
        }
        base.withdraw(amount);
    }
}
