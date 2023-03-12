package nio;

import java.nio.IntBuffer;

public class buffer {
    public static void main(String[] args) {
        IntBuffer allocate = IntBuffer.allocate(5);
        for (int i = 0; i < 4; i++) {
            allocate.put(i * 2);
        }
        System.out.println(allocate.position());
        //读写转换
        allocate.flip();
        while (allocate.hasRemaining()) {
            System.out.println(allocate.get());
        }
        System.out.println(allocate.position());
    }
}
