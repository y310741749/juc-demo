package test.study123;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test3 {

    public static void main(String[] args){
        new Thread(()->{
            while (true){
                try {
                    Thread.sleep(1000);
                    System.out.println("aaa");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();
        new Thread(()->{
            while (true){
                try {
                    Thread.sleep(1000);
                    System.out.println("bbb");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t2").start();
    }
}
