package eventManager;

import eventHandler.ISubList;
import eventSource.IMessageSource;
import pojo.Message;

/**
 * 消息管理器接口
 * 维护消息，从消息源接收并分发消息给对应的消息接收者
 */
public interface IMessageManager {
    /**
     * 接收消息
     * 会将消息存储起来
     * @param message 待接收的消息
     */
    public void receiveMessage(Message message);

    /**
     * 分发消息
     * 将消息分发给对应注册的消息接收者
     * @param message 待分发的消息
     */
    public void distributeMessage(Message message);

    /**
     * 添加消息源
     * @param source 待添加的消息源
     */
    public void addSource(IMessageSource source);

    /**
     * 添加订阅者(消息接收者)列表
     * 订阅者列表中的所有消息接收者会向该消息源注册
     * 之后会接收到该消息源发送的消息
     * @param source 消息源名称
     * @param subs 待添加的订阅者列表
     */
    public void addSubs(IMessageSource source,ISubList subs);
}
