package socket;

import java.io.*;

public class Test2 {
    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("D:\\result.txt"), true)));
        writer.write("123");
        writer.flush();
        writer.close();
    }
}
