package test.study4;

public class Test7 {


    public static void main(String[] args) throws InterruptedException {
        GuardedObject object=new GuardedObject();
        Thread t1 = new Thread(() -> {
            object.setObject("123");
        });

        Thread t2 = new Thread(() -> {
            try {
                Object object1 = object.getObject();
                System.out.println(object1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t1.join();
        t2.start();
    }


}

class GuardedObject{
    private Object object;

    public Object getObject() throws InterruptedException {
        synchronized (this){
            while (object==null){
                wait();
            }
        }
        return object;
    }

    public void setObject(Object object) {
        synchronized (this){
            this.object = object;
            notify();
        }

    }
}
