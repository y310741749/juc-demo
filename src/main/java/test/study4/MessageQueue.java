package test.study4;

import java.util.LinkedList;

public class MessageQueue {
    private final LinkedList<Message> list=new LinkedList<>();
    private int capcity=20;

    public Message take() throws InterruptedException {
        synchronized (list){
            while (list.isEmpty()){
                list.wait();
            }
            Message message = list.removeLast();
            list.notifyAll();
            return message;
        }
    }

    public void put(Message message) throws InterruptedException {
        synchronized (list){
            while (list.size()==capcity){
                wait();
            }
            list.addFirst(message);
            list.notifyAll();
        }
    }
}
