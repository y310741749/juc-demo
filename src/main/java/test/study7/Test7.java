package test.study7;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.charset.Charset;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 */
@Slf4j
public class Test7 {
    public static void main(String[] args) {
        DataContainer dc = new DataContainer();
        ThreadUtil.execute(() -> {
            for (int i = 0; i < 10; i++) {
                log.info("第{}次写入", (i + 1));
                dc.write((i + 1) + "");
                ThreadUtil.sleep(1000);
            }

        });
        ThreadUtil.sleep(1000);
        ThreadUtil.execute(() -> {
            while (true){
                String read = dc.read();
                log.debug("读取成功，数据为:{}", read);
            }
        });
    }
}

@Slf4j
class DataContainer {


    ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock r = rw.readLock();

    ReentrantReadWriteLock.WriteLock w = rw.writeLock();

    public String read() {
//        r.lock();
//        try {
            ThreadUtil.sleep(500);
            log.debug("开始读");
            return FileUtil.readString(new File("test.txt"), Charset.defaultCharset());

//        } finally {
//            log.debug("释放读锁");
//            r.unlock();
//        }
    }

    public void write(String s) {
        w.lock();
        try {
            log.debug("开始写");
            FileUtil.appendString(s, new File("test.txt"), Charset.defaultCharset());
        } finally {
            log.debug("释放写锁");
            w.unlock();
        }
    }
}
