package Practice_Projects;

abstract class Account{
    private int accountNumber;
    private String accountHolderName;
    private double balance;

    Account(int accountNumber, String accountHolder, double balance){
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolder;
        this.balance = balance;
    }

    public int getAccountNumber(){
        return accountNumber;
    }

    public void setAccountNumber(int aNumber){
        accountNumber = aNumber;
    }

    public String getAccountHolder(){
        return accountHolderName;
    }

    public void setAccountHolderName(String aName){
        accountHolderName = aName;
    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double aBalance){
        balance = aBalance;
    }

    public void deposit(double amount){
        if (amount > 0) {
            balance += amount;
            System.out.println("Amount deposited successfully.");
        }else{
            System.out.println("Invalid Amount.");
        }
    }

    public void withdraw(double amount){
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Amount withdrawn successfully.");
        }else{
            System.out.println("Insufficient balance or Invalid Amount.");
        }
    }

    public void checkBalance(){
        System.out.println("Your Balance is: "+balance);
    }

    abstract public void showAccountDetails();
}

class SavingAccount extends Account{
    private double interestRate = 0.04;

    public SavingAccount(int accountNumber, String holderName, double balance){
        super(accountNumber, holderName, balance);
    }

    public void addInterest(){
        double interest = getBalance() * interestRate;
        setBalance(getBalance() + interest);
        System.out.println("Interest added: "+interest);
    }

    public void showAccountDetails(){
        System.out.println("~Saving Account~");
        System.out.println("Account Number : "+getAccountNumber());
        System.out.println("Account Holder Name : "+getAccountHolder());
        System.out.println("Account Balance : "+getBalance());
    }

}

class CurrentAccount extends Account{
    private int overDraftLimit = 1000;

    public CurrentAccount(int accountNumber, String holderName, double balance){
        super(accountNumber, holderName, balance);
    }

    public void withdraw(int amount){
        if (amount > 0 && amount <= getBalance()+overDraftLimit) {
            double afterWithdraw = getBalance() - amount;
            setBalance(afterWithdraw);
        }
    }
    public void showAccountDetails(){
        System.out.println("~Saving Account~");
        System.out.println("Account Number : "+getAccountNumber());
        System.out.println("Account Holder Name : "+getAccountHolder());
        System.out.println("Account Balance : "+getBalance());
    }

}