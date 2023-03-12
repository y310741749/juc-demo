import cn.hutool.core.io.file.FileReader;

import java.io.FileNotFoundException;

public class yibu {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        System.out.println("开始读文件");
        new Thread(()->{
            FileReader fileReader = new FileReader("D:\\img\\Redis on Windows.docx");
            fileReader.getInputStream();
            System.out.println("文件读取完成");
        }).start();

        System.out.println("do other thing");
        Thread.sleep(10000);
    }
}
