package eventHandler;

import pojo.Message;
import pojo.ISubscriber;

/**
 * 订阅者列表接口
 * 维护订阅者列表
 * 负责向订阅者发送消息
 */
public interface ISubList {

    /**
     * 向订阅者列表中添加订阅者
     * @param ISubscriber 订阅者对象
     */
    public void attachSubscriber(ISubscriber ISubscriber);

    /**
     * 从订阅者列表中删除订阅者
     * @param ISubscriber 订阅者对象
     */
    public void detachSubscriber(ISubscriber ISubscriber);

    /**
     * 展示该列表的所有订阅者
     */
    public void showSubscribers();

    /**
     * 接收来自MessageManager的消息，并发给订阅者
     * @param message 待接收的消息
     */
    public void receiveMessage(Message message);
}
