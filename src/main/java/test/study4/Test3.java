package test.study4;

import java.util.ArrayList;
import java.util.List;

public class Test3 {
        public static void main(String[] args) {
        ThreadUnsafe threadUnsafe = new ThreadUnsafe();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                threadUnsafe.method();
            }).start();
        }
    }

}
 class ThreadUnsafe {
    List<String> list = new ArrayList<>();
    synchronized void method() {
        for (int i = 0; i < 100; i++) {
            list.add("1");
            list.remove(0);
        }
    }

}
