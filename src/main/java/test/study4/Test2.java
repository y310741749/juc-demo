package test.study4;

public class Test2 {
    public static void main(String[] args) {
        TestRunable testRunable = new TestRunable();
        Thread t1 = new Thread(testRunable);
        Thread t2 = new Thread(testRunable);
        t1.start();
        t2.start();
    }
}
