class Bank {
    public String name;
    public int age;
    public String address;
    public int balance;
    public Bank(String name, int age, String address, int balance) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.balance = balance;
    }
}

class Account extends Bank {

    public Account(String name, int age, String address, int balance) {
        super(name, age, address, balance);
    }

    public void withdrawal(int amt) {
        if (balance >= amt) {   
            balance = balance - amt;
            System.out.println("Successfully withdrawn. Your balance is: " + balance);
        } else {
            System.out.println("Not sufficient balance");
        }
    }

    public void deposit(int amt) {
        balance = balance + amt;
        System.out.println("Successfully deposited. Your balance is: " + balance);
    }

    public void checkBalance() {
        System.out.println("Your balance is: " + balance);
    }
}

class Main {
    public static void main(String[] args) {
        Account acc = new Account("Pankaj", 22, "Delhi", 5000);

        acc.checkBalance();
        acc.deposit(2000);
        acc.withdrawal(3000);
        acc.withdrawal(6000);
    }
}