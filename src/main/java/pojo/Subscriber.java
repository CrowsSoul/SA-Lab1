package pojo;

import java.util.ArrayList;
import java.util.List;

public class Subscriber implements ISubscriber {
    private final String name;
    private final List<Message> messages = new ArrayList<Message>();

    /**
     * 构造方法
     * @param name 订阅者名称
     */
    public Subscriber(String name) {
        this.name = name;
    }

    /**
     * 获取订阅者名称
     * @return 订阅者名称
     */
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void receiveMessage(Message message) {
        messages.add(message);
    }

    @Override
    public void checkMessage(int index) {
        messages.get(index).show();
    }

    @Override
    public void showMessages() {
        int i = 1;
        for (Message message : messages) {
            System.out.println(i + ". " + message.getTitle());
            i++;
        }
    }
}
