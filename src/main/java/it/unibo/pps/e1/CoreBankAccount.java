package it.unibo.pps.e1;

class CoreBankAccount implements BankAccount{

    private int balance = 0;

    @Override
    public int getBalance() {
        return this.balance;
    }

    @Override
    public void deposit(int amount) {
        this.balance = this.balance + amount;
    }

    @Override
    public void withdraw(int amount) {
        this.balance = this.balance - amount;
    }
}
