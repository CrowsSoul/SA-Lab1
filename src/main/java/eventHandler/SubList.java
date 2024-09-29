package eventHandler;

import pojo.Message;
import pojo.ISubscriber;

import java.util.ArrayList;
import java.util.List;

public class SubList implements ISubList {
    private final String name; // 订阅者列表名称
    private final List<ISubscriber> subscribers = new ArrayList<ISubscriber>();

    /**
     * 构造方法
     * @param name 订阅者名单名称
     */
    public SubList(String name) {
        this.name = name;
    }

    /**
     * 获取订阅者名单名称
     * @return 订阅者名单名称
     */
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void attachSubscriber(ISubscriber ISubscriber) {
        //将订阅者添加到订阅者名单中
        subscribers.add(ISubscriber);
    }

    @Override
    public void detachSubscriber(ISubscriber ISubscriber) {
        //将订阅者从订阅者名单中移除
        subscribers.remove(ISubscriber);
    }

    @Override
    public void showSubscribers() {
        //显示订阅者名单中的所有订阅者
        for (ISubscriber subscriber : subscribers) {
            System.out.println(subscriber.getName());
        }
    }

    @Override
    public void receiveMessage(Message message) {
        //向订阅者名单中的所有订阅者发送消息
        for (ISubscriber subscriber : subscribers) {
            subscriber.receiveMessage(message);
        }
    }
}
