package test.study123;

public class Test7 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t");
        t.start();
        System.out.println(t.getState());
        Thread.sleep(500);
        System.out.println(t.getState());
        Thread.sleep(2000);
        System.out.println(t.getState());
    }
}
