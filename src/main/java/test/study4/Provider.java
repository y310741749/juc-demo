package test.study4;


import java.util.Scanner;

public class Provider {
    static MessageQueue messageQueue = new MessageQueue();

    public static void main(String[] args) throws InterruptedException {
        int id = 0;
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("等待输入...");
            if(scanner.hasNextLine()){
                String s = scanner.nextLine();
                Message message = new Message(++id, s);
                messageQueue.put(message);
            }
        }
    }

}
