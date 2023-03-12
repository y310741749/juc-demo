package test.study5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Test3 {
    public static void main(String[] args) {
        Test3.demo(()->new AtomicLong(0),(obj)->obj.getAndIncrement());
        Test3.demo(()->new LongAdder(),(obj)->obj.increment());
    }

    private static <T> void demo(Supplier<T> addSupplier, Consumer<T> action){
        T t = addSupplier.get();
        List<Thread> list=new ArrayList<>();
        for (int i=0;i<4;i++){
            list.add(new Thread(()->{
                for(int j=0;j<500000;j++){
                    action.accept(t);
                }

            }));
        }
        long l1 = System.nanoTime();
        list.forEach(s->s.start());
        list.forEach(l-> {
            try {
                l.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("耗时:"+(System.nanoTime()-l1));
        System.out.println(t);

    }
}
