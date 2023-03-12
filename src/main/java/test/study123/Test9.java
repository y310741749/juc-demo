package test.study123;

import java.io.*;

public class Test9 {
    public static void main(String[] args) throws IOException {
        File file = new File("D://Demo1.java");
        File file1 = new File("D://Demo2.java");
        InputStream is = new FileInputStream(file);
        OutputStream os = new FileOutputStream(file1);
        byte b[] = new byte[1024];
        int length = 0;
//        System.out.println(is.read(b));
        while ((length=is.read(b))!= -1) {
            for (int i=0;i<b.length;i++){
                System.out.println(b[i]);
            }
            os.write(b);
        }
    }
}
