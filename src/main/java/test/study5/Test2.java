package test.study5;

import cn.hutool.core.thread.ThreadUtil;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

public class Test2 {
    public static void main(String[] args) {
        Account account = new Account();
        for (int i = 0; i < 100; i++) {
            ThreadUtil.execute(() -> {
                account.del();
            });
        }

        ThreadUtil.sleep(1000);
        System.out.println(account.get());

    }
}

class Account {
    AtomicReference<BigDecimal> decimal;

    public Account() {
        this.decimal = new AtomicReference<>(new BigDecimal(1000));
    }

    void del() {
        BigDecimal prev, next;
        do {
            prev = decimal.get();
            next = prev.subtract(BigDecimal.TEN);
        } while (!decimal.compareAndSet(prev, next));
    }

    BigDecimal get() {
        return decimal.get();
    }
}
