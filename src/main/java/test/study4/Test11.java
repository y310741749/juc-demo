package test.study4;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.locks.ReentrantLock;

public class Test11 {
    public static void main(String[] args) {
        kuaizi kuaizi1 = new kuaizi(1);
        kuaizi kuaizi2 = new kuaizi(2);
        kuaizi kuaizi3 = new kuaizi(3);
        kuaizi kuaizi4 = new kuaizi(4);
        kuaizi kuaizi5 = new kuaizi(5);
        People a = new People(kuaizi1, kuaizi2, "a");
        People b = new People(kuaizi2, kuaizi3, "b");
        People c = new People(kuaizi3, kuaizi4, "c");
        People d = new People(kuaizi4, kuaizi5, "d");
        People e = new People(kuaizi5, kuaizi1, "e");
        new Thread(a).start();
        new Thread(b).start();
        new Thread(c).start();
        new Thread(d).start();
        new Thread(e).start();
    }
}

class People implements Runnable {
    private ReentrantLock left;
    private ReentrantLock right;
    private String name;

    public People(ReentrantLock left, ReentrantLock right, String name) {
        this.left = left;
        this.right = right;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            if(left.tryLock()){
                try {
                    if(right.tryLock()){
                        try {
                            System.out.println(name + "在吃饭");
                            ThreadUtil.sleep(1000);
                        }finally {
                            right.unlock();
                        }

                    }
                }finally {
                    left.unlock();
                }
            }
        }
    }
}

class kuaizi extends ReentrantLock {
    private int id; //筷子id

    public kuaizi(int id) {
        this.id = id;
    }
}
