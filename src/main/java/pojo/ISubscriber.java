package pojo;

/**
 * 消息的接收者接口
 * 也叫做订阅者
 */
public interface ISubscriber {
    /**
     * 接收消息
     * @param message 接收到的消息
     */
    public void receiveMessage(Message message);

    /**
     * 查看具体消息
     * @param index 消息的在表中的序号
     */
    public void checkMessage(int index);

    /**
     * 展示消息列表
     */
    public void showMessages();

    /**
     * 获取订阅者的名字
     * @return 订阅者的名字
     */
    public String getName();
}
