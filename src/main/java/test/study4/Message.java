package test.study4;

public class Message {
    private int id;
    private Object data;

    public Message(int id, Object data) {
        this.id = id;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", data=" + data +
                '}';
    }
}
