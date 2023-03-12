package test.study4;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Scanner;

@Slf4j
public class Test8 {
    public static void main(String[] args) {


        Thread t2 = new Thread(() -> {


        });
        t2.setName("消费者");
        t2.start();
    }
}
