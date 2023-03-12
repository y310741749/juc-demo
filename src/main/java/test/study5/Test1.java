package test.study5;

import java.util.concurrent.atomic.AtomicInteger;

public class Test1 {
    public static void main(String[] args) {
        AtomicInteger i=new AtomicInteger(2);
        i.updateAndGet(x->x*5);
        System.out.println(i.get());
    }
}
