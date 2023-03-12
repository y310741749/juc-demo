package test.study123;

public abstract class Test11 {
    protected int id;
    private String name;

    abstract void getId();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Test11(String name) {
        this.name=name;
    }

    void getId2(){
        System.out.println(id);
    }
}
