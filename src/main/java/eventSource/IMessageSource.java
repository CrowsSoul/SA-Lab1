package eventSource;

import pojo.Message;

public interface IMessageSource {
    /**
     * 发送消息
     * @param message 待发送的消息
     */
    public void sendMessage(Message message);

    /**
     * 创建消息
     * @param title 标题
     * @param content 内容
     */
    public Message createMessage(String title,String content);

    /**
     * 获取消息源名称
     * @return 消息源名称
     */
    public String getName();
}
