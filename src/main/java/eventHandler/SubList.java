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
     * @param name 订阅者列表名称
     */
    public SubList(String name) {
        this.name = name;
    }

    /**
     * 获取订阅者列表名称
     * @return 订阅者列表名称
     */
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void attachSubscriber(ISubscriber ISubscriber) {

    }

    @Override
    public void detachSubscriber(ISubscriber ISubscriber) {

    }

    @Override
    public void showSubscribers() {

    }

    @Override
    public void receiveMessage(Message message) {

    }
}
