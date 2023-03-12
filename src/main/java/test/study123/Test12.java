package test.study123;

public class Test12 extends Test11 {
    public Test12(String name) {
        super(name);
        this.id=13;
    }

    @Override
    void getId() {
        System.out.println(id);
    }

    public static void main(String[] args) {
        Test11 t= new Test12("杨鹏");
        System.out.println(t.getName());
        t.getId2();
    }
}
