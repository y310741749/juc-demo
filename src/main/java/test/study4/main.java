package test.study4;

import cn.hutool.core.thread.ThreadUtil;

public class main {
    public static void main(String[] args) {
        Temp temp = new Temp();
        new Thread(()->{
            ThreadUtil.sleep(5000);
            temp.setTp(true);
        }).start();
        while (true) {
            if(temp.getTp()){
                System.out.println("true");
                break;
            }
            System.out.println("false");
            ThreadUtil.sleep(1000);
        }

    }
}

class Temp {
    private boolean tp = false;

    public boolean getTp() {
        return tp;
    }

    public void setTp(boolean tp) {
        this.tp = tp;
    }
}
