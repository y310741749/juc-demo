package test.study4;

import java.util.Random;

public class Test4 {
    public static void main(String[] args) throws InterruptedException {
        Account a1=new Account(1000);
        Account a2=new Account(1000);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                a1.transfer(a2, new Random().nextInt(100) + 1);
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                a2.transfer(a1, new Random().nextInt(100) + 1);
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("余额:"+(a1.getMoney()+a2.getMoney()));
    }
}
class Account{
    private int money;

    public Account(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public  void transfer(Account target,int amount){
        synchronized (Account.class){
            if(this.money>=amount){
                this.setMoney(this.getMoney()-amount);
                target.setMoney(target.getMoney()+amount);
            }
        }

    }
}
