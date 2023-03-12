package test.study7;

import java.util.ArrayList;
import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        demo1 demo1 = new demo1(list);
        demo1.dayin();
        list.add("3");
        demo1.dayin();
    }
}

class demo1 {
    private List<String> list;

    public demo1(List<String> list) {
        this.list = list;
    }

    public void dayin() {
        for (String s : list) {
            System.out.println("s:" + s);
        }
        System.out.println("=============");
    }
}
