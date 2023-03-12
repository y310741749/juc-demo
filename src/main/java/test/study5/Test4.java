package test.study5;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class Test4 {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe o = (Unsafe) theUnsafe.get(null);
        System.out.println(o);
        SimpleDateFormat dateFormat=new SimpleDateFormat();
//        dateFormat.format()
//        DateTimeFormatter yy = DateTimeFormatter.ofPattern("yy");
//        yy.format("2012-02");
    }
}
